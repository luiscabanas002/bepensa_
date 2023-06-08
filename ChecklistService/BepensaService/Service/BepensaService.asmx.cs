using BepensaService.CheckModel;
using BepensaService.Models;
using ExcelDataReader;
using Newtonsoft.Json;
using OfficeOpenXml;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace BepensaService.Service
{
    /// <summary>
    /// Summary description for BepensaService
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class BepensaService : System.Web.Services.WebService
    {
        public static string TIPO_AREA_ACTO_INSEGURO = "ACTO INSEGURO";
        public static string TIPO_AREA_CONDICIONES = "CONDICIONES";
        public static string TIPO_AREA_AMBIENTAL = "AMBIENTAL";

        Bepensa_Context db = new Bepensa_Context();

        #region Registro de usuario
        private List<string> getPaises()
        {
            List<string> listPaises = new List<string>();
            try
            {
                var paises = db.usuarios.Select(x => x.pais).Distinct().ToList();
                if (paises != null && paises.Count > 0)
                {
                    return paises;
                }
                else
                {
                    listPaises = new List<string>();
                    listPaises.Add("México");
                }
            }
            catch (Exception e)
            {
                listPaises = new List<string>();
                listPaises.Add("México");
            }
            return listPaises;
        }

        private List<procesos> getProcesos()
        {
            List<procesos> list = new List<procesos>();
            try
            {
                var objects = db.procesos.Where(x => x.id_estatus == 1).Distinct().ToList().OrderBy(x => x.nombre);
                foreach (var item in objects)
                {
                    if (list.FirstOrDefault(x => x.id_proceso == item.id_proceso) == null)
                    {
                        procesos itemAdd = new procesos();
                        itemAdd.id_proceso = item.id_proceso;
                        itemAdd.nombre = item.nombre;
                        foreach (var itemSubProceso in item.sub_procesos)
                        {
                            sub_procesos sub = new sub_procesos();
                            sub.id_sub_proceso = itemSubProceso.id_sub_proceso;
                            sub.nombre = itemSubProceso.nombre;
                            itemAdd.sub_procesos.Add(sub);
                        }
                        if (itemAdd.sub_procesos != null && itemAdd.sub_procesos.Count > 0)
                        {
                            list.Add(itemAdd);
                        }
                    }
                }
            }
            catch (Exception e)
            {
                list = new List<procesos>();
            }
            return list;
        }

        private List<sub_sub_procesos> getSubSubProcesos()
        {
            List<sub_sub_procesos> list = new List<sub_sub_procesos>();
            try
            {
                var objects = db.sub_sub_procesos.Where(x => x.id_estatus == 1).Distinct().ToList().OrderBy(x => x.nombre);
                foreach (var item in objects)
                {
                    if (list.FirstOrDefault(x => x.id_sub_sub_proceso == item.id_sub_sub_proceso) == null)
                    {
                        sub_sub_procesos itemAdd = new sub_sub_procesos();
                        itemAdd.id_sub_sub_proceso = item.id_sub_sub_proceso;
                        itemAdd.nombre = item.nombre;
                        list.Add(itemAdd);
                    }
                }
            }
            catch (Exception e)
            {
                list = new List<sub_sub_procesos>();
            }
            return list;
        }

        private List<puestos> getPuestos()
        {
            List<puestos> list = new List<puestos>();
            try
            {
                var objects = db.puestos.Where(x => x.id_estatus == 1).Distinct().ToList().OrderBy(x => x.nombre);
                foreach (var item in objects)
                {
                    if (list.FirstOrDefault(x => x.nombre.Equals(item.nombre)) == null)
                    {
                        puestos itemAdd = new puestos();
                        itemAdd.id_puesto = item.id_puesto;
                        itemAdd.nombre = item.nombre;
                        list.Add(itemAdd);
                    }
                }
            }
            catch (Exception e)
            {
                list = new List<puestos>();
            }
            return list;
        }

        private List<regiones> getRegiones()
        {
            List<regiones> list = new List<regiones>();
            try
            {
                var objects = db.regiones.ToList();
                foreach (var item in objects.ToList())
                {
                    if (list.FirstOrDefault(x => x.id_region == item.id_region) == null)
                    {
                        regiones itemAdd = new regiones();
                        itemAdd.id_region = item.id_region;
                        itemAdd.Region_nombre = item.Region_nombre;
                        list.Add(itemAdd);
                    }
                }
            }
            catch (Exception e)
            {
                list = new List<regiones>();
            }
            return list;
        }

        private List<menu_ejecutivo> getMenuEjecutivo()
        {
            List<menu_ejecutivo> list = new List<menu_ejecutivo>();
            try
            {
                var objects = db.menu_ejecutivo.Distinct().ToList().OrderBy(x => x.Observaciones);
                foreach (var item in objects)
                {
                    if (list.FirstOrDefault(x => x.ID_ejecutivo == item.ID_ejecutivo) == null)
                    {
                        menu_ejecutivo itemAdd = new menu_ejecutivo();
                        itemAdd.ID_ejecutivo = item.ID_ejecutivo;
                        itemAdd.Observaciones = item.Observaciones;
                        list.Add(itemAdd);
                    }
                }
            }
            catch (Exception e)
            {
                list = new List<menu_ejecutivo>();
            }
            return list;
        }

        private List<divisiones> getDivision()
        {
            List<divisiones> list = new List<divisiones>();
            try
            {
                var objects = db.divisiones.Distinct().ToList().OrderBy(x => x.descripcion);
                foreach (var item in objects)
                {
                    if (list.FirstOrDefault(x => x.id_division == item.id_division) == null)
                    {
                        divisiones itemAdd = new divisiones();
                        itemAdd.id_division = item.id_division;
                        itemAdd.descripcion = item.descripcion;
                        list.Add(itemAdd);
                    }
                }
            }
            catch (Exception e)
            {
                list = new List<divisiones>();
                divisiones itemAdd = new divisiones();
                itemAdd.id_division = -1;
                itemAdd.descripcion = e.ToString();
                list.Add(itemAdd);
            }
            return list;
        }
        #endregion

        private List<areas> getAreas(int idDivision, string tipo)
        {
            List<areas> listAreas = new List<areas>();
            try
            {
                List<areas> areas = new List<Models.areas>();
                if (idDivision != -1)
                {
                    areas = db.rel_areas_divisiones.Where(x => x.id_division == idDivision && x.areas.id_estatus == 1).Select(x => x.areas).Where(x => x.tipo.Equals(tipo) && x.id_estatus == 1).OrderBy(x => x.num_posicion).ToList();
                }
                else
                {
                    areas = db.areas.Where(x => x.tipo.Equals(tipo) && x.id_estatus == 1).OrderBy(x => x.num_posicion).ToList();
                }

                foreach (areas item in areas)
                {
                    if (listAreas.FirstOrDefault(x => x.id_area == item.id_area) == null)
                    {
                        areas area = new areas();
                        area.id_area = item.id_area;
                        area.id_estatus = item.id_estatus;
                        area.nombre = item.nombre;
                        area.path_icono = item.path_icono;
                        area.num_posicion = item.num_posicion;
                        area.tipo = item.tipo;
                        listAreas.Add(area);
                    }
                }
            }
            catch (Exception e)
            {
                listAreas = new List<areas>();
                areas itemAdd = new areas();
                itemAdd.nombre = e.ToString();
                listAreas.Add(itemAdd);
            }

            return listAreas;
        }

        private List<sucursales> getSucursales(int idDivision)
        {
            List<sucursales> listSucursales = new List<sucursales>();
            try
            {
                List<sucursales> listDB = new List<sucursales>();
                if (idDivision == 1)
                {
                    string nombre_sin_sucursal = ConfigurationManager.AppSettings["SinSucursal"].ToString();
                    listDB = db.sucursales.Where(x => !x.nombre.ToUpper().Equals(nombre_sin_sucursal.ToUpper().ToString())).OrderBy(x => x.nombre).ToList();
                }
                else
                {
                    if (idDivision != -1)
                    {
                        listDB = db.rel_toc.Where(x => x.idDivision == idDivision).Select(x => x.sucursales).OrderBy(x => x.nombre).ToList();
                    }else
                    {
                        listDB = db.rel_toc.ToList().Select(x => x.sucursales).OrderBy(x => x.nombre).ToList();
                    }
                }

                foreach (sucursales item in listDB)
                {
                    if (listSucursales.FirstOrDefault(X => X.id_sucursal == item.id_sucursal) == null)
                    {
                        sucursales oSucursal = new sucursales();
                        oSucursal.id_sucursal = item.id_sucursal;
                        oSucursal.nombre = item.nombre;
                        listSucursales.Add(oSucursal);
                    }
                }

            }
            catch (Exception e)
            {
                listSucursales = new List<sucursales>();
            }
            return listSucursales;
        }

        private List<origenes_observaciones> getOrigenesObservaciones()
        {
            List<origenes_observaciones> listOrigenes = new List<origenes_observaciones>();
            try
            {
                var origenes = db.origenes_observaciones.Where(x => x.id_estatus == 1).ToList().OrderBy(x => x.nombre);
                foreach (origenes_observaciones item in origenes)
                {
                    if (listOrigenes.FirstOrDefault(x => x.id_origen_observacion == item.id_origen_observacion) == null)
                    {
                        origenes_observaciones origenObservacion = new origenes_observaciones();
                        origenObservacion.id_origen_observacion = item.id_origen_observacion;
                        origenObservacion.id_estatus = item.id_estatus;
                        origenObservacion.nombre = item.nombre;
                        listOrigenes.Add(origenObservacion);
                    }
                }
            }
            catch (Exception e)
            {
                listOrigenes = new List<origenes_observaciones>();
            }
            return listOrigenes;
        }

        private List<riesgos_identificados> getRiesgosIdentificados()
        {
            List<riesgos_identificados> listRiesgos = new List<riesgos_identificados>();
            try
            {
                var origenes = db.riesgos_identificados.Where(x => x.id_estatus == 1).ToList().OrderBy(x => x.nombre);
                foreach (riesgos_identificados item in origenes)
                {
                    if (listRiesgos.FirstOrDefault(x => x.id_riesgo_identificado == item.id_riesgo_identificado) == null)
                    {
                        riesgos_identificados riesgoIdentificado = new riesgos_identificados();
                        riesgoIdentificado.id_riesgo_identificado = item.id_riesgo_identificado;
                        riesgoIdentificado.id_estatus = item.id_estatus;
                        riesgoIdentificado.nombre = item.nombre;
                        listRiesgos.Add(riesgoIdentificado);
                    }
                }
            }
            catch (Exception e)
            {
                listRiesgos = new List<riesgos_identificados>();
            }
            return listRiesgos;
        }

        public List<tipos_factores> getTiposFactores()
        {
            List<tipos_factores> listTiposFactores = new List<tipos_factores>();
            try
            {
                var origenes = db.tipos_factores.ToList().OrderBy(x => x.nombre);
                foreach (tipos_factores item in origenes)
                {
                    if (listTiposFactores.FirstOrDefault(x => x.id_tipo_factor == item.id_tipo_factor) == null)
                    {
                        tipos_factores tipoFactor = new tipos_factores();
                        tipoFactor.id_tipo_factor = item.id_tipo_factor;
                        tipoFactor.id_estatus = item.id_estatus;
                        tipoFactor.nombre = item.nombre;
                        tipoFactor.desMensaje1 = item.desMensaje1;
                        tipoFactor.desMensaje2 = item.desMensaje2;
                        tipoFactor.factores = FormatListFactores(db.factores.Where(x => x.id_tipo_factor == item.id_tipo_factor && x.id_estatus == 1).OrderBy(x => x.num_posicion).ToList());
                        listTiposFactores.Add(tipoFactor);
                    }
                }
            }
            catch (Exception e)
            {
                listTiposFactores = new List<tipos_factores>();
            }
            return listTiposFactores;
        }

        private List<factores> FormatListFactores(List<factores> listFactores)
        {
            List<factores> listReturn = new List<factores>();

            foreach (factores item in listFactores)
            {
                factores factor = new factores();
                factor.id_factor = item.id_factor;
                factor.id_estatus = item.id_estatus;
                factor.id_tipo_factor = item.id_tipo_factor;
                factor.nombre = item.nombre;
                factor.path_icono = item.path_icono;
                factor.num_posicion = item.num_posicion;
                listReturn.Add(factor);
            }

            return listReturn;
        }

        [WebMethod]
        public void getDivisiones()
        {
            ResponseObject response = new ResponseObject();
            try
            {
                var divisionesList = db.divisiones.ToList();
                if (divisionesList != null && divisionesList.Count > 0)
                {
                    List<divisiones> divisionesReturn = new List<Models.divisiones>();
                    foreach (var item in divisionesList)
                    {
                        divisiones oDivision = new divisiones();
                        oDivision.id_division = item.id_division;
                        oDivision.descripcion = item.descripcion;
                        oDivision.aplica_codigo = item.aplica_codigo;
                        oDivision.aplica_sucursal = item.aplica_sucursal;
                        divisionesReturn.Add(oDivision);
                    }


                    response.Data = divisionesReturn;
                    response.Estatus = 1;
                    response.Mensaje = "Operación realizada con éxito";
                }
                else
                {
                    response.Data = null;
                    response.Estatus = 0;
                    response.Mensaje = "No se encontraron elementos";
                }
            }
            catch (Exception e)
            {
                response.Data = null;
                response.Estatus = -1;
                response.Mensaje = e.ToString();
            }
            Response(JsonConvert.SerializeObject(response));
        }

        [WebMethod]
        public void getDataCheckList()
        {
            ResponseObject response = new ResponseObject();
            try
            {
                DataCheckModel dat = new DataCheckModel();
                dat.Areas = getAreas(1, TIPO_AREA_ACTO_INSEGURO);
                dat.Origenes = getOrigenesObservaciones();
                dat.Riesgos = getRiesgosIdentificados();
                dat.TiposFactores = getTiposFactores();
                dat.Sucursales = getSucursales(1);

                response.Data = dat;
                response.Estatus = 1;
                response.Mensaje = "Operación realizada con éxito";

            }
            catch (Exception e)
            {
                response.Data = null;
                response.Estatus = -1;
                response.Mensaje = e.ToString();
            }
            Response(JsonConvert.SerializeObject(response));
        }

        [WebMethod]
        public void getDataCheckList_v2(string json)
        {
            ResponseObject response = new ResponseObject();
            try
            {
                var division = JsonConvert.DeserializeObject<divisiones>(json);

                DataCheckModel dat = new DataCheckModel();
                dat.Areas = getAreas(division.id_division, TIPO_AREA_ACTO_INSEGURO);
                dat.AreasCondiciones = getAreas(division.id_division, TIPO_AREA_CONDICIONES);
                dat.AreasAmbiental = getAreas(division.id_division, TIPO_AREA_AMBIENTAL);
                dat.Origenes = getOrigenesObservaciones();
                dat.Riesgos = getRiesgosIdentificados();
                dat.TiposFactores = getTiposFactores();
                dat.Sucursales = getSucursales(division.id_division);

                response.Data = dat;
                response.Estatus = 1;
                response.Mensaje = "Operación realizada con éxito";

            }
            catch (Exception e)
            {
                response.Data = null;
                response.Estatus = -1;
                response.Mensaje = e.ToString();
            }
            Response(JsonConvert.SerializeObject(response));
        }

        [WebMethod]
        public void recuperarUsuario(string json)
        {
            ResponseObject response = new ResponseObject();
            try
            {
                var usuario = JsonConvert.DeserializeObject<usuarios>(json);
                var division = db.divisiones.FirstOrDefault(x => x.id_division == (usuario.Id_division == null ? 1 : usuario.Id_division));

                var listUser = db.usuarios.Where(x => x.clave == usuario.clave).ToList();
                if (listUser != null)
                {
                    foreach (var validateUsuario in listUser)
                    {
                        if (validateUsuario.Id_division == usuario.Id_division)
                        {
                            usuarios usuarioReturn = new usuarios();
                            usuarioReturn.clave = validateUsuario.clave;
                            usuarioReturn.clave_puesto = validateUsuario.clave_puesto;
                            usuarioReturn.clave_sucursal = validateUsuario.clave_sucursal;
                            usuarioReturn.id_sucursal = validateUsuario.id_sucursal;
                            usuarioReturn.departamento = validateUsuario.departamento;
                            usuarioReturn.id_estatus = validateUsuario.id_estatus;
                            usuarioReturn.id_usuario = validateUsuario.id_usuario;
                            usuarioReturn.nombre = validateUsuario.nombre;
                            usuarioReturn.nombre_puesto = validateUsuario.nombre_puesto;
                            usuarioReturn.nombre_sucursal = validateUsuario.nombre_sucursal;
                            usuarioReturn.proceso = validateUsuario.proceso;
                            usuarioReturn.sub_proceso = validateUsuario.sub_proceso;


                            response.Data = usuarioReturn;
                            response.Estatus = 1;
                            response.Mensaje = "Operación realizada con éxito";
                            break;
                        }
                        else
                        {                           
                            string mensaje = division == null ? "El usuario no pertenece a la división que selecciono" : $"El usuario no pertenece a la división {division.descripcion.ToUpper().Trim()}";
                            response.Estatus = 0;
                            response.Mensaje = mensaje;
                        }
                    }
                }
                else
                {
                    response.Data = null;
                    response.Estatus = 0;
                    response.Mensaje = "Clave incorrecta";
                }
            }
            catch (Exception e)
            {
                response.Data = null;
                response.Estatus = -1;
                response.Mensaje = e.ToString();
            }
            Response(JsonConvert.SerializeObject(response));
        }

        [WebMethod]
        public void enviarEncuesta_v2(string json)
        {

            string nombre_invitado = ConfigurationManager.AppSettings["Invitado"].ToString();
            string nombre_sin_sucursal = ConfigurationManager.AppSettings["SinSucursal"].ToString();

            ResponseObject response = new ResponseObject();
            try
            {
                var registro = JsonConvert.DeserializeObject<registros_toc>(json);

                if (registro.id_sucursal == -1)
                {
                    var suc = db.sucursales.Where(x => x.nombre.ToUpper().Equals(nombre_sin_sucursal.ToUpper())).FirstOrDefault();
                    if (suc != null)
                    {
                        registro.id_sucursal = suc.id_sucursal;
                    }
                }
                else
                {
                    var suc = db.sucursales.Where(x => x.id_sucursal == registro.id_sucursal).FirstOrDefault();
                    if (suc != null)
                    {
                        registro.adicional_suc = suc.nombre;
                    }
                }

                registros_toc _regisro_toc = new registros_toc();
                _regisro_toc.id_usuario = registro.id_usuario;
                _regisro_toc.id_area = registro.id_area;
                _regisro_toc.id_factor = registro.id_factor;
                _regisro_toc.id_origen_observacion = registro.id_origen_observacion;
                _regisro_toc.id_sucursal = registro.id_sucursal;
                _regisro_toc.maquina_equipo = registro.maquina_equipo;
                _regisro_toc.observaciones = registro.observaciones;
                _regisro_toc.acciones_a_tomar = registro.acciones_a_tomar;
                _regisro_toc.id_division = registro.id_division;
                _regisro_toc.adicional_suc = registro.adicional_suc;
                _regisro_toc.fecha_hora_registro = DateTime.Now;
                _regisro_toc.token = registro.token;

                db.registros_toc.Add(_regisro_toc);
                if (db.SaveChanges() > 0)
                {
                    var validate = db.usuarios.Where(x => x.id_usuario == registro.id_usuario).FirstOrDefault();
                    if (validate != null)
                    {
                        string msj = "¡Ya terminaste!";
                        if (!validate.nombre.ToUpper().Equals(nombre_invitado.ToUpper().ToString()))
                        {
                            var numRegistros = db.registros_toc.Where(x => x.id_usuario == registro.id_usuario).ToList();
                            msj = "¡Llevas " + numRegistros.Count + " " + (numRegistros.Count > 1 ? "levantamientos" : "levantamiento") + "!";
                        }

                        response.Data = msj;
                        response.Estatus = 1;
                        response.Mensaje = "Operación realizada con éxito";
                    }
                    else
                    {
                        response.Data = null;
                        response.Estatus = 1;
                        response.Mensaje = "Operación realizada con éxito";
                    }
                }
                else
                {
                    response.Data = null;
                    response.Estatus = 0;
                    response.Mensaje = "No se pudo registrar la información";
                }

            }
            catch (Exception e)
            {
                response.Data = null;
                response.Estatus = -1;
                response.Mensaje = e.ToString();
            }
            Response(JsonConvert.SerializeObject(response));
        }

        [WebMethod]
        public void enviarEncuesta_condiciones_v2(string json)
        {

            string nombre_invitado = ConfigurationManager.AppSettings["Invitado"].ToString();
            string nombre_sin_sucursal = ConfigurationManager.AppSettings["SinSucursal"].ToString();

            ResponseObject response = new ResponseObject();
            try
            {
                var registro = JsonConvert.DeserializeObject<registros_condiciones>(json);

                if (registro.id_sucursal == -1)
                {
                    var suc = db.sucursales.Where(x => x.nombre.ToUpper().Equals(nombre_sin_sucursal.ToUpper())).FirstOrDefault();
                    if (suc != null)
                    {
                        registro.id_sucursal = suc.id_sucursal;
                    }
                }
                else
                {
                    var suc = db.sucursales.Where(x => x.id_sucursal == registro.id_sucursal).FirstOrDefault();
                    if (suc != null)
                    {
                        registro.adicional_suc = suc.nombre;
                    }
                }

                registros_condiciones _regisro_condiciones = new registros_condiciones();
                _regisro_condiciones.id_usuario = registro.id_usuario;
                _regisro_condiciones.id_area = registro.id_area;
                _regisro_condiciones.id_factor = registro.id_factor;
                _regisro_condiciones.id_origen_observacion = registro.id_origen_observacion;
                _regisro_condiciones.id_sucursal = registro.id_sucursal;
                _regisro_condiciones.maquina_equipo_zona = registro.maquina_equipo_zona;
                _regisro_condiciones.observaciones = registro.observaciones;
                _regisro_condiciones.acciones_a_tomar = registro.acciones_a_tomar;
                _regisro_condiciones.id_division = registro.id_division;
                _regisro_condiciones.adicional_suc = registro.adicional_suc;
                _regisro_condiciones.fecha_hora_registro = DateTime.Now;
                _regisro_condiciones.atendido = false;
                _regisro_condiciones.token = registro.token;

                db.registros_condiciones.Add(_regisro_condiciones);
                if (db.SaveChanges() > 0)
                {
                    var validate = db.usuarios.Where(x => x.id_usuario == registro.id_usuario).FirstOrDefault();
                    if (validate != null)
                    {
                        string msj = "¡Ya terminaste!";
                        if (!validate.nombre.ToUpper().Equals(nombre_invitado.ToUpper().ToString()))
                        {
                            var numRegistros = db.registros_condiciones.Where(x => x.id_usuario == registro.id_usuario).ToList();
                            msj = "¡Llevas " + numRegistros.Count + " " + (numRegistros.Count > 1 ? "levantamientos" : "levantamiento") + "!";
                        }

                        response.Data = msj;
                        response.Estatus = 1;
                        response.Mensaje = "Operación realizada con éxito";
                    }
                    else
                    {
                        response.Data = null;
                        response.Estatus = 1;
                        response.Mensaje = "Operación realizada con éxito";
                    }
                }
                else
                {
                    response.Data = null;
                    response.Estatus = 0;
                    response.Mensaje = "No se pudo registrar la información";
                }

            }
            catch (Exception e)
            {
                response.Data = null;
                response.Estatus = -1;
                response.Mensaje = e.ToString();
            }
            Response(JsonConvert.SerializeObject(response));
        }

        [WebMethod]
        public void enviarEncuesta_ambientales_v2(string json)
        {

            string nombre_invitado = ConfigurationManager.AppSettings["Invitado"].ToString();
            string nombre_sin_sucursal = ConfigurationManager.AppSettings["SinSucursal"].ToString();

            ResponseObject response = new ResponseObject();
            try
            {
                var registro = JsonConvert.DeserializeObject<registros_ambientales>(json);

                if (registro.id_sucursal == -1)
                {
                    var suc = db.sucursales.Where(x => x.nombre.ToUpper().Equals(nombre_sin_sucursal.ToUpper())).FirstOrDefault();
                    if (suc != null)
                    {
                        registro.id_sucursal = suc.id_sucursal;
                    }
                }
                else
                {
                    var suc = db.sucursales.Where(x => x.id_sucursal == registro.id_sucursal).FirstOrDefault();
                    if (suc != null)
                    {
                        registro.adicional_suc = suc.nombre;
                    }
                }

                registros_ambientales _regisro_ambientales = new registros_ambientales();
                _regisro_ambientales.id_usuario = registro.id_usuario;
                _regisro_ambientales.id_area = registro.id_area;
                _regisro_ambientales.id_factor = registro.id_factor;
                _regisro_ambientales.id_origen_observacion = registro.id_origen_observacion;
                _regisro_ambientales.id_sucursal = registro.id_sucursal;
                _regisro_ambientales.maquina_equipo = registro.maquina_equipo;
                _regisro_ambientales.observaciones = registro.observaciones;
                _regisro_ambientales.acciones_a_tomar = registro.acciones_a_tomar;
                _regisro_ambientales.id_division = registro.id_division;
                _regisro_ambientales.adicional_suc = registro.adicional_suc;
                _regisro_ambientales.fecha_hora_registro = DateTime.Now;
                _regisro_ambientales.atendido = false;
                _regisro_ambientales.token = registro.token;

                db.registros_ambientales.Add(_regisro_ambientales);
                if (db.SaveChanges() > 0)
                {
                    var validate = db.usuarios.Where(x => x.id_usuario == registro.id_usuario).FirstOrDefault();
                    if (validate != null)
                    {
                        string msj = "¡Ya terminaste!";
                        if (!validate.nombre.ToUpper().Equals(nombre_invitado.ToUpper().ToString()))
                        {
                            var numRegistros = db.registros_ambientales.Where(x => x.id_usuario == registro.id_usuario).ToList();
                            msj = "¡Llevas " + numRegistros.Count + " " + (numRegistros.Count > 1 ? "levantamientos" : "levantamiento") + "!";
                        }

                        response.Data = msj;
                        response.Estatus = 1;
                        response.Mensaje = "Operación realizada con éxito";
                    }
                    else
                    {
                        response.Data = null;
                        response.Estatus = 1;
                        response.Mensaje = "Operación realizada con éxito";
                    }
                }
                else
                {
                    response.Data = null;
                    response.Estatus = 0;
                    response.Mensaje = "No se pudo registrar la información";
                }

            }
            catch (Exception e)
            {
                response.Data = null;
                response.Estatus = -1;
                response.Mensaje = e.ToString();
            }
            Response(JsonConvert.SerializeObject(response));
        }

        [WebMethod]
        public void enviarEncuesta(string json)
        {
            ResponseObject response = new ResponseObject();
            try
            {
                string nombre_invitado = ConfigurationManager.AppSettings["Invitado"].ToString();
                string nombre_sin_sucursal = ConfigurationManager.AppSettings["SinSucursal"].ToString();

                var registro = JsonConvert.DeserializeObject<registros_toc>(json);

                if (registro.id_sucursal == -1)
                {
                    var suc = db.sucursales.Where(x => x.nombre.ToUpper().Equals(nombre_sin_sucursal.ToUpper())).FirstOrDefault();
                    if (suc != null)
                    {
                        registro.id_sucursal = suc.id_sucursal;
                        registro.adicional_suc = suc.nombre;
                    }
                }
                else
                {
                    var suc = db.sucursales.Where(x => x.id_sucursal == registro.id_sucursal).FirstOrDefault();
                    if (suc != null)
                    {
                        registro.adicional_suc = suc.nombre;
                    }
                }

                registros_toc _regisro_toc = new registros_toc();
                _regisro_toc.id_usuario = registro.id_usuario;
                _regisro_toc.id_area = registro.id_area;
                _regisro_toc.id_factor = registro.id_factor;
                _regisro_toc.id_origen_observacion = registro.id_origen_observacion;
                _regisro_toc.id_sucursal = registro.id_sucursal;
                _regisro_toc.maquina_equipo = registro.maquina_equipo;
                _regisro_toc.observaciones = registro.observaciones;
                _regisro_toc.acciones_a_tomar = registro.acciones_a_tomar;
                _regisro_toc.fecha_hora_registro = DateTime.Now;
                _regisro_toc.adicional_suc = registro.adicional_suc;
                _regisro_toc.id_division = 1;

                db.registros_toc.Add(_regisro_toc);
                if (db.SaveChanges() > 0)
                {
                    var validate = db.usuarios.Where(x => x.id_usuario == registro.id_usuario).FirstOrDefault();
                    if (validate != null)
                    {
                        int count = -1;
                        if (!validate.nombre.ToUpper().Equals(nombre_invitado.ToUpper().ToString()))
                        {
                            var numRegistros = db.registros_toc.Where(x => x.id_usuario == registro.id_usuario).ToList();
                            count = numRegistros.Count;
                        }

                        response.Data = count;
                        response.Estatus = 1;
                        response.Mensaje = "Operación realizada con éxito";
                    }
                    else
                    {
                        response.Data = null;
                        response.Estatus = 1;
                        response.Mensaje = "Operación realizada con éxito";
                    }
                }
                else
                {
                    response.Data = null;
                    response.Estatus = 0;
                    response.Mensaje = "No se pudo registrar la información";
                }

            }
            catch (Exception e)
            {
                response.Data = null;
                response.Estatus = -1;
                response.Mensaje = e.ToString();
            }
            Response(JsonConvert.SerializeObject(response));
        }

        [WebMethod]
        public void registrarDispositivo(string json)
        {

            ResponseObject response = new ResponseObject();
            try
            {
                var registro = JsonConvert.DeserializeObject<dispositivos>(json);

                dispositivos dis = new dispositivos();
                dis.token = registro.token;
                dis.so = registro.so;

                db.dispositivos.Add(dis);
                if (db.SaveChanges() > 0)
                {
                    response.Data = null;
                    response.Estatus = 1;
                    response.Mensaje = "Operación realizada con éxito";
                }
                else
                {
                    response.Data = null;
                    response.Estatus = 1;
                    response.Mensaje = "Operación realizada con éxito";
                }
            }
            catch (Exception e)
            {
                response.Data = null;
                response.Estatus = -1;
                response.Mensaje = e.ToString();
            }
            Response(JsonConvert.SerializeObject(response));
        }

        [WebMethod]
        public void sicronizar(string json)
        {
            ResponseObject response = new ResponseObject();

            using (var transaction = db.Database.BeginTransaction())
            {
                try
                {
                    var datos = JsonConvert.DeserializeObject<List<ViewModelSincronizar>>(json);

                    string nombre_invitado = ConfigurationManager.AppSettings["Invitado"].ToString();
                    string nombre_sin_sucursal = ConfigurationManager.AppSettings["SinSucursal"].ToString();

                    foreach (var registro in datos)
                    {
                        if (registro.tipo.Equals(TIPO_AREA_ACTO_INSEGURO))
                        {

                            if (registro.id_sucursal == -1)
                            {
                                var suc = db.sucursales.Where(x => x.nombre.ToUpper().Equals(nombre_sin_sucursal.ToUpper())).FirstOrDefault();
                                if (suc != null)
                                {
                                    registro.id_sucursal = suc.id_sucursal;
                                }
                            }
                            else
                            {
                                var suc = db.sucursales.Where(x => x.id_sucursal == registro.id_sucursal).FirstOrDefault();
                                if (suc != null)
                                {
                                    registro.adicional_suc = suc.nombre;
                                }
                            }

                            registros_toc _regisro_toc = new registros_toc();
                            _regisro_toc.id_usuario = registro.id_usuario;
                            _regisro_toc.id_area = registro.id_area;
                            _regisro_toc.id_factor = registro.id_factor;
                            _regisro_toc.id_origen_observacion = registro.id_origen_observacion;
                            _regisro_toc.id_sucursal = registro.id_sucursal;
                            _regisro_toc.maquina_equipo = registro.maquina_equipo;
                            _regisro_toc.observaciones = registro.observaciones;
                            _regisro_toc.acciones_a_tomar = registro.acciones_a_tomar;
                            _regisro_toc.id_division = registro.id_division;
                            _regisro_toc.adicional_suc = registro.adicional_suc;
                            _regisro_toc.fecha_hora_registro = DateTime.Now;
                            _regisro_toc.token = registro.token;

                            db.registros_toc.Add(_regisro_toc);
                            if (db.SaveChanges() > 0)
                            {
                                continue;
                            }
                            else
                            {
                                transaction.Rollback();
                                response.Estatus = 1;
                                response.Mensaje = $"Ocurrió un error en la sincronización";
                                break;
                            }

                        }
                        else if (registro.tipo.Equals(TIPO_AREA_CONDICIONES))
                        {
                            if (registro.id_sucursal == -1)
                            {
                                var suc = db.sucursales.Where(x => x.nombre.ToUpper().Equals(nombre_sin_sucursal.ToUpper())).FirstOrDefault();
                                if (suc != null)
                                {
                                    registro.id_sucursal = suc.id_sucursal;
                                }
                            }
                            else
                            {
                                var suc = db.sucursales.Where(x => x.id_sucursal == registro.id_sucursal).FirstOrDefault();
                                if (suc != null)
                                {
                                    registro.adicional_suc = suc.nombre;
                                }
                            }

                            registros_condiciones _regisro_condiciones = new registros_condiciones();
                            _regisro_condiciones.id_usuario = registro.id_usuario;
                            _regisro_condiciones.id_area = registro.id_area;
                            _regisro_condiciones.id_factor = registro.id_factor;
                            _regisro_condiciones.id_origen_observacion = registro.id_origen_observacion;
                            _regisro_condiciones.id_sucursal = registro.id_sucursal;
                            _regisro_condiciones.maquina_equipo_zona = registro.maquina_equipo;
                            _regisro_condiciones.observaciones = registro.observaciones;
                            _regisro_condiciones.acciones_a_tomar = registro.acciones_a_tomar;
                            _regisro_condiciones.id_division = registro.id_division;
                            _regisro_condiciones.adicional_suc = registro.adicional_suc;
                            _regisro_condiciones.fecha_hora_registro = DateTime.Now;
                            _regisro_condiciones.atendido = false;
                            _regisro_condiciones.token = registro.token;

                            db.registros_condiciones.Add(_regisro_condiciones);
                            if (db.SaveChanges() > 0)
                            {
                                continue;
                            }
                            else
                            {
                                transaction.Rollback();
                                response.Estatus = 1;
                                response.Mensaje = $"Ocurrió un error en la sincronización";
                                break;
                            }
                        }
                        else if (registro.tipo.Equals(TIPO_AREA_AMBIENTAL))
                        {
                            if (registro.id_sucursal == -1)
                            {
                                var suc = db.sucursales.Where(x => x.nombre.ToUpper().Equals(nombre_sin_sucursal.ToUpper())).FirstOrDefault();
                                if (suc != null)
                                {
                                    registro.id_sucursal = suc.id_sucursal;
                                }
                            }
                            else
                            {
                                var suc = db.sucursales.Where(x => x.id_sucursal == registro.id_sucursal).FirstOrDefault();
                                if (suc != null)
                                {
                                    registro.adicional_suc = suc.nombre;
                                }
                            }

                            registros_ambientales _regisro_ambientales = new registros_ambientales();
                            _regisro_ambientales.id_usuario = registro.id_usuario;
                            _regisro_ambientales.id_area = registro.id_area;
                            _regisro_ambientales.id_factor = registro.id_factor;
                            _regisro_ambientales.id_origen_observacion = registro.id_origen_observacion;
                            _regisro_ambientales.id_sucursal = registro.id_sucursal;
                            _regisro_ambientales.maquina_equipo = registro.maquina_equipo;
                            _regisro_ambientales.observaciones = registro.observaciones;
                            _regisro_ambientales.acciones_a_tomar = registro.acciones_a_tomar;
                            _regisro_ambientales.id_division = registro.id_division;
                            _regisro_ambientales.adicional_suc = registro.adicional_suc;
                            _regisro_ambientales.fecha_hora_registro = DateTime.Now;
                            _regisro_ambientales.atendido = false;
                            _regisro_ambientales.token = registro.token;

                            db.registros_ambientales.Add(_regisro_ambientales);
                            if (db.SaveChanges() > 0)
                            {
                                continue;
                            }
                            else
                            {
                                transaction.Rollback();
                                response.Estatus = 1;
                                response.Mensaje = $"Ocurrió un error en la sincronización";
                                break;
                            }
                        }
                    }

                    transaction.Commit();
                    response.Estatus = 1;
                    response.Mensaje = $"Operación realizada con éxito, se han sincronizado los registros";
                }
                catch (Exception ex)
                {
                    transaction.Rollback();
                    response.Estatus = 1;
                    response.Mensaje = $"Ocurrió un error en la sincronización";
                }
            }
            Response(JsonConvert.SerializeObject(response));
        }

        [WebMethod]
        public void getInfoUser()
        {
            ResponseObject response = new ResponseObject();
            try
            {
                DataUserRegister dat = new DataUserRegister();
                dat.Sucursales = getSucursales(1);
                dat.Paises = getPaises();
                dat.Divisiones = getDivision();
                dat.MenuEjecutivo = getMenuEjecutivo();
                dat.Procesos = getProcesos();
                dat.Puestos = getPuestos();
                dat.Regiones = getRegiones().OrderBy(X => X.nombre).ToList();
                dat.SubSubProcesos = getSubSubProcesos();

                response.Data = dat;
                response.Estatus = 1;
                response.Mensaje = "Operación realizada con éxito";

            }
            catch (Exception e)
            {
                response.Data = null;
                response.Estatus = -1;
                response.Mensaje = e.ToString();
            }
            Response(JsonConvert.SerializeObject(response));
        }

        [WebMethod]
        public void getInfoRegistro()
        {
            ResponseObject response = new ResponseObject();
            try
            {
                DataUserRegister dat = new DataUserRegister();
                dat.Paises = getPaises();
                dat.Divisiones = getDivision();
                dat.Puestos = getPuestos();


                response.Data = dat;
                response.Estatus = 1;
                response.Mensaje = "Operación realizada con éxito";

            }
            catch (Exception e)
            {
                response.Data = null;
                response.Estatus = -1;
                response.Mensaje = e.ToString();
            }
            Response(JsonConvert.SerializeObject(response));
        }

        [WebMethod]
        public void getInfoDivision(int idDivision)
        {
            ResponseObject response = new ResponseObject();
            try
            {
                List<DataItemProceso> listSubProcesos = new List<DataItemProceso>();
                if (idDivision == 1)
                {
                    var sucursales = db.sucursales.Where(x => x.id_estatus == 1).ToList();
                    var procesos = db.procesos.Where(x => x.id_estatus == 1).ToList();
                    procesos = procesos.Where(x => x.sub_procesos.Count > 0).ToList();
                    foreach (var item in procesos)
                    {
                        DataItemProceso dataItemProceso = new DataItemProceso();
                        dataItemProceso.id_proceso = item.id_proceso;
                        dataItemProceso.nombre = item.nombre;
                        dataItemProceso.sub_procesos = new List<DataItemSubProceso>();
                        foreach (var itemSubProceso in item.sub_procesos)
                        {
                            DataItemSubProceso dataItemSubprocesos = new DataItemSubProceso();
                            dataItemSubprocesos.id_sub_proceso = itemSubProceso.id_sub_proceso;
                            dataItemSubprocesos.nombre = itemSubProceso.nombre;
                            dataItemSubprocesos.sucursales = new List<DataItemSucursal>();
                            foreach (var itemSucursal in sucursales)
                            {
                                DataItemSucursal dataItemSucursal = new DataItemSucursal();
                                dataItemSucursal.id_sucursal = itemSucursal.id_sucursal;
                                dataItemSucursal.nombre = itemSucursal.nombre;
                                dataItemSubprocesos.sucursales.Add(dataItemSucursal);
                            }
                            dataItemProceso.sub_procesos.Add(dataItemSubprocesos);
                        }
                        listSubProcesos.Add(dataItemProceso);
                    }
                }
                else
                {
                    var rel = db.rel_toc.Where(x => x.idDivision == idDivision);
                    var procesos = rel.Where(x => x.idDivision == idDivision).Select(x => x.procesos).ToList().Distinct();
                    foreach (var item in procesos)
                    {
                        DataItemProceso dataItemProceso = new DataItemProceso();
                        dataItemProceso.id_proceso = item.id_proceso;
                        dataItemProceso.nombre = item.nombre;
                        dataItemProceso.sub_procesos = new List<DataItemSubProceso>();
                        var subProcesos = rel.Where(x => x.idDivision == idDivision && x.idProceso == dataItemProceso.id_proceso).Select(x => x.sub_procesos).ToList().Distinct();
                        foreach (var itemSubProceso in subProcesos)
                        {
                            DataItemSubProceso dataItemSubprocesos = new DataItemSubProceso();
                            dataItemSubprocesos.id_sub_proceso = itemSubProceso.id_sub_proceso;
                            dataItemSubprocesos.nombre = itemSubProceso.nombre;
                            dataItemSubprocesos.sucursales = new List<DataItemSucursal>();
                            var sucursales = rel.Where(x => x.idDivision == idDivision && x.idProceso == dataItemProceso.id_proceso && x.idSubProceso == itemSubProceso.id_sub_proceso).Select(x => x.sucursales).ToList().Distinct();
                            foreach (var itemSucursal in sucursales)
                            {
                                DataItemSucursal dataItemSucursal = new DataItemSucursal();
                                dataItemSucursal.id_sucursal = itemSucursal.id_sucursal;
                                dataItemSucursal.nombre = itemSucursal.nombre;
                                dataItemSubprocesos.sucursales.Add(dataItemSucursal);
                            }
                            dataItemProceso.sub_procesos.Add(dataItemSubprocesos);
                        }
                        listSubProcesos.Add(dataItemProceso);
                    }
                }

                response.Data = (listSubProcesos != null && listSubProcesos.Count > 0) ? listSubProcesos : null;
                response.Estatus = (listSubProcesos != null && listSubProcesos.Count > 0) ? 1 : 0;
                response.Mensaje = (listSubProcesos != null && listSubProcesos.Count > 0) ? "Operación realizada con éxito" : "No se encontraron elementos";

            }
            catch (Exception e)
            {
                response.Data = null;
                response.Estatus = -1;
                response.Mensaje = e.ToString();
            }
            Response(JsonConvert.SerializeObject(response));
        }

        [WebMethod]
        public void registrarUsuario(string json)
        {
            ResponseObject response = new ResponseObject();
            try
            {
                var registro = JsonConvert.DeserializeObject<usuarios>(json);
                var validateUser = db.usuarios.FirstOrDefault(x => x.clave.ToUpper().Equals(registro.clave.ToUpper().ToString()));
                if (validateUser == null)
                {
                    var rel_toc_usuario = db.rel_toc.FirstOrDefault(x => x.idDivision == registro.Id_division && x.idProceso == registro.id_proceso && x.idSubProceso == registro.id_sub_proceso && x.idSucursal == registro.id_sucursal);

                    usuarios usuarioNew = new usuarios();
                    usuarioNew.clave = registro.clave;
                    usuarioNew.nombre = registro.nombre.ToUpper();
                    usuarioNew.compañia = "Pendiente de registrar";
                    usuarioNew.pais = registro.pais;
                    usuarioNew.id_proceso = registro.id_proceso;
                    usuarioNew.id_sub_proceso = registro.id_sub_proceso;
                    usuarioNew.id_sub_sub_proceso = 1;
                    usuarioNew.id_puesto = registro.id_puesto;
                    usuarioNew.id_region = rel_toc_usuario != null ? rel_toc_usuario.idRegion : 1;
                    usuarioNew.Id_ejecutivo = 1;
                    usuarioNew.Id_division = registro.Id_division;
                    usuarioNew.id_sucursal = registro.id_sucursal;
                    usuarioNew.id_estatus = 1;
                    usuarioNew.app = 1;
                    usuarioNew.Reg_timestamp = DateTime.Now;

                    var sucursal = db.sucursales.FirstOrDefault(x => x.id_sucursal == registro.id_sucursal);
                    usuarioNew.clave_sucursal = (sucursal != null && sucursal.clave != null && sucursal.clave != "") ? sucursal.clave : "-";
                    usuarioNew.nombre_sucursal = (sucursal != null && sucursal.nombre != null && sucursal.nombre != "") ? sucursal.nombre : "-";

                    var puesto = db.puestos.FirstOrDefault(x => x.id_puesto == registro.id_puesto);
                    usuarioNew.clave_puesto = puesto != null ? puesto.clave : "-";
                    usuarioNew.nombre_puesto = puesto != null ? puesto.nombre : "-";

                    usuarioNew.departamento = "0000";

                    db.usuarios.Add(usuarioNew);
                    if (db.SaveChanges() > 0)
                    {
                        response.Data = null;
                        response.Estatus = 1;
                        response.Mensaje = "Operación realizada con éxito";
                    }
                    else
                    {
                        response.Data = null;
                        response.Estatus = 0;
                        response.Mensaje = "No se pudo registrar la información";
                    }
                }
                else
                {
                    response.Data = null;
                    response.Estatus = 0;
                    response.Mensaje = "La clave que intenta registrar ya se encuentra en uso.";
                }

            }
            catch (Exception e)
            {
                response.Data = null;
                response.Estatus = -1;
                response.Mensaje = e.ToString();
            }
            Response(JsonConvert.SerializeObject(response));
        }

        public void Response(string json)
        {
            System.Web.HttpResponse response = System.Web.HttpContext.Current.Response;
            response.Clear();
            response.Buffer = true;
            response.Charset = "utf-8";
            response.ContentEncoding = System.Text.Encoding.UTF8;
            response.ContentType = "application/json";
            response.AddHeader("Content-Disposition",
                                  "attachment; filename=" + "data.json" + ";");
            response.Flush();
            response.Output.Write(json);
            response.End();
        }

        [WebMethod]
        public void CargarExcelTOCS()
        {
            String ruta = System.Web.Hosting.HostingEnvironment.MapPath("~/Upload/RegistroToc.xlsx");
            using (FileStream stream = System.IO.File.Open(ruta, FileMode.Open, FileAccess.ReadWrite))
            {
                using (ExcelPackage excel = new ExcelPackage())
                {
                    using (var transaction = db.Database.BeginTransaction())
                    {
                        excel.Load(stream);
                        ExcelWorksheet ws = excel.Workbook.Worksheets["RegistroTOC"];
                        int count = 0;
                        for (int i = 2; i <= ws.Dimension.Rows; i++)
                        {
                            string division = ws.Cells[i, 1].Text.ToString().ToUpper().Trim();
                            string proceso = ws.Cells[i, 2].Text.ToString().ToUpper().Trim();
                            string subproceso = ws.Cells[i, 3].Text.ToString().ToUpper().Trim();
                            string sucursal = ws.Cells[i, 4].Text.ToString().ToUpper().Trim();
                            string region = ws.Cells[i, 5].Text.ToString().ToUpper().Trim();

                            divisiones div = db.divisiones.FirstOrDefault(x => x.descripcion.Equals(division));
                            procesos proc = db.procesos.FirstOrDefault(x => x.nombre.Equals(proceso) && x.id_estatus == 2);
                            sub_procesos sub = db.sub_procesos.FirstOrDefault(x => x.nombre.Equals(subproceso) && x.id_estatus == 2);
                            sucursales suc = db.sucursales.FirstOrDefault(x => x.nombre.Equals(sucursal) && x.id_estatus == 2);
                            regiones reg = db.regiones.FirstOrDefault(x => x.nombre.Equals(region));

                            rel_toc rel = new rel_toc();
                            //Division
                            if (div != null)
                            {
                                rel.idDivision = div.id_division;
                            }
                            //Proceso
                            if (proc != null)
                            {
                                rel.idProceso = proc.id_proceso;
                            }
                            else
                            {
                                rel.procesos = new procesos();
                                rel.procesos.id_estatus = 2;
                                rel.procesos.nombre = proceso;
                            }
                            //Subproceso
                            if (sub != null)
                            {
                                rel.idSubProceso = sub.id_sub_proceso;
                            }
                            else
                            {
                                rel.sub_procesos = new sub_procesos();
                                rel.sub_procesos.id_estatus = 2;
                                rel.sub_procesos.nombre = subproceso;
                            }
                            //Sucursal
                            if (suc != null)
                            {
                                rel.idSucursal = suc.id_sucursal;
                            }
                            else
                            {
                                rel.sucursales = new sucursales();
                                rel.sucursales.id_estatus = 2;
                                rel.sucursales.nombre = sucursal;
                            }
                            //Region
                            if (reg != null)
                            {
                                rel.idRegion = reg.id_region;
                            }
                            else
                            {
                                rel.regiones = new regiones();
                                rel.regiones.Region_nombre = region;
                                rel.regiones.nombre = region;
                            }

                            db.rel_toc.Add(rel);
                            if (db.SaveChanges() > 0)
                            {
                                count++;
                            }
                        }
                        if (count == (ws.Dimension.Rows - 1))
                        {
                            transaction.Commit();
                        }
                    }
                }
            }
        }

        [WebMethod]
        public void CargarExcelUsuarios()
        {
            int count = 0;
            try
            {
                String ruta = System.Web.Hosting.HostingEnvironment.MapPath("~/Upload/UsuariosMotriz.xlsx");
                using (FileStream stream = System.IO.File.Open(ruta, FileMode.Open, FileAccess.ReadWrite))
                {
                    using (ExcelPackage excel = new ExcelPackage())
                    {
                        using (var transaction = db.Database.BeginTransaction())
                        {
                            excel.Load(stream);
                            ExcelWorksheet ws = excel.Workbook.Worksheets["RegistroTOC"];
                            
                            for (int i = 2; i <= ws.Dimension.Rows; i++)
                            {
                                string proceso = ws.Cells[i, 3].Text.ToString().ToUpper().Trim();
                                string subproceso = ws.Cells[i, 4].Text.ToString().ToUpper().Trim();
                                string sucursal = ws.Cells[i, 5].Text.ToString().ToUpper().Trim();
                                string clave_empleado = ws.Cells[i, 6].Text.ToString().ToUpper().Trim();
                                string nombre_empleado = ws.Cells[i, 7].Text.ToString().ToUpper().Trim();
                                string puesto_empleado = ws.Cells[i, 8].Text.ToString().ToUpper().Trim();
                                string division = ws.Cells[i, 9].Text.ToString().ToUpper().Trim();


                                divisiones div = db.divisiones.FirstOrDefault(x => x.descripcion.Equals(division));
                                procesos proc = db.procesos.FirstOrDefault(x => x.nombre.Equals(proceso) && x.id_estatus == 2);
                                sub_procesos sub = db.sub_procesos.FirstOrDefault(x => x.nombre.Equals(subproceso) && x.id_estatus == 2);
                                sucursales suc = db.sucursales.FirstOrDefault(x => x.nombre.Equals(sucursal) && x.id_estatus == 2);
                                puestos puesto = db.puestos.FirstOrDefault(x => x.nombre.Equals(puesto_empleado));

                                usuarios usuarioNew = new usuarios();
                                usuarioNew.clave = clave_empleado;
                                usuarioNew.nombre = nombre_empleado.ToUpper();
                                usuarioNew.compañia = "Pendiente de registrar";
                                usuarioNew.pais = "México";



                                usuarioNew.id_sub_sub_proceso = 1;
                                usuarioNew.id_region = 6; //No aplica
                                usuarioNew.Id_ejecutivo = 1;

                                usuarioNew.id_estatus = 1;
                                usuarioNew.app = 1;
                                usuarioNew.Reg_timestamp = DateTime.Now;

                                usuarioNew.departamento = "0000";
                                usuarioNew.clave_puesto = puesto != null ? puesto.clave : "-";
                                usuarioNew.nombre_puesto = puesto != null ? puesto.nombre : "-";


                                usuarioNew.clave_sucursal = "-";
                                usuarioNew.nombre_sucursal = "-";
                                usuarioNew.departamento = "NewMotriz";

                                //Division
                                if (div != null)
                                {
                                    usuarioNew.Id_division = div.id_division;
                                }

                                //Proceso
                                if (proc != null)
                                {
                                    usuarioNew.id_proceso = proc.id_proceso;
                                }
                                else
                                {
                                    usuarioNew.procesos = new procesos();
                                    usuarioNew.procesos.id_estatus = 2;
                                    usuarioNew.procesos.nombre = proceso;
                                }
                                //Subproceso
                                if (sub != null)
                                {
                                    usuarioNew.id_sub_proceso = sub.id_sub_proceso;
                                }
                                else
                                {
                                    usuarioNew.sub_procesos = new sub_procesos();
                                    usuarioNew.sub_procesos.id_estatus = 2;
                                    usuarioNew.sub_procesos.nombre = subproceso;
                                }
                                //Sucursal
                                if (suc != null)
                                {
                                    usuarioNew.id_sucursal = suc.id_sucursal;
                                }
                                else
                                {
                                    usuarioNew.sucursales = new sucursales();
                                    usuarioNew.sucursales.id_estatus = 2;
                                    usuarioNew.sucursales.nombre = sucursal;
                                }
                                //Puesto
                                if (puesto != null)
                                {
                                    usuarioNew.id_puesto = puesto.id_puesto;
                                }
                                else
                                {
                                    usuarioNew.puestos = new puestos();
                                    usuarioNew.puestos.id_estatus = 1;
                                    usuarioNew.puestos.nombre = puesto_empleado;
                                    usuarioNew.puestos.clave = "-";
                                }

                                db.usuarios.Add(usuarioNew);
                                if (db.SaveChanges() > 0)
                                {
                                    count++;
                                }
                            }
                            if (count == (ws.Dimension.Rows - 1))
                            {
                                transaction.Commit();
                            }
                        }
                    }
                }
            }
            catch (Exception e)
            {
                string erro = e.ToString();
            }
        }
    }
}
