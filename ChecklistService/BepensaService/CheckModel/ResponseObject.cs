using BepensaService.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace BepensaService.CheckModel
{
    public class ResponseObject
    {
        public int Estatus { get; set; }
        public object Data { get; set; }
        public string Mensaje { get; set; }
    }

    public class DataCheckModel
    {
        public List<areas> Areas { get; set; }
        public List<areas> AreasCondiciones { get; set; }
        public List<areas> AreasAmbiental { get; set; }
        public List<origenes_observaciones> Origenes { get; set; }
        public List<riesgos_identificados> Riesgos { get; set; }
        public List<tipos_factores> TiposFactores { get; set; }
        public List<sucursales> Sucursales { get; set; }
    }

    public class DataUserRegister
    {
        public List<sucursales> Sucursales { get; set; }
        public List<string> Paises { get; set; }
        public List<procesos> Procesos { get; set; }
        public List<sub_sub_procesos> SubSubProcesos { get; set; }
        public List<puestos> Puestos { get; set; }
        public List<regiones> Regiones { get; set; }
        public List<menu_ejecutivo> MenuEjecutivo { get; set; }
        public List<divisiones> Divisiones { get; set; }

    }

    public class DataItemProceso
    {
        public int id_proceso { get; set; }
        public string nombre { get; set; }
        public List<DataItemSubProceso> sub_procesos { get; set; }
    }

    public class DataItemSubProceso
    {
        public int id_sub_proceso { get; set; }
        public string nombre { get; set; }
        public List<DataItemSucursal> sucursales { get; set; }
    }

    public class DataItemSucursal
    {
        public int id_sucursal { get; set; }
        public string nombre { get; set; }
    }

    public class DataItemDivision
    {
        List<DataItemProceso> procesos { get; set; }
    }

    public class ViewModelSincronizar
    {

        public long id_usuario { get; set; }

        public int id_area { get; set; }

        public int id_factor { get; set; }

        public int id_origen_observacion { get; set; }

        public int id_sucursal { get; set; }

        public string observaciones { get; set; }

        public string acciones_a_tomar { get; set; }

        public string maquina_equipo { get; set; }

        public DateTime fecha_hora_registro { get; set; }

        public long id_division { get; set; }

        public string adicional_suc { get; set; }

        public bool atendido { get; set; }

        public DateTime? fecha_hora_atendido { get; set; }

        public string comentarios_atencion { get; set; }

        public string token { get; set; }

        public string tipo { get; set; }

    }

}