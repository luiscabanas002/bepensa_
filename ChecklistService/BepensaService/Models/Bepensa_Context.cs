namespace BepensaService.Models
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    public partial class Bepensa_Context : DbContext
    {
        public Bepensa_Context()
            : base("name=Bepensa_Context_produccion")
        {
        }

        public virtual DbSet<areas> areas { get; set; }
        public virtual DbSet<dispositivos> dispositivos { get; set; }
        public virtual DbSet<divisiones> divisiones { get; set; }
        public virtual DbSet<emailconfig> emailconfig { get; set; }
        public virtual DbSet<estatus> estatus { get; set; }
        public virtual DbSet<factores> factores { get; set; }
        public virtual DbSet<menu_ejecutivo> menu_ejecutivo { get; set; }
        public virtual DbSet<meses> meses { get; set; }
        public virtual DbSet<origenes_observaciones> origenes_observaciones { get; set; }
        public virtual DbSet<procesos> procesos { get; set; }
        public virtual DbSet<puestos> puestos { get; set; }
        public virtual DbSet<regiones> regiones { get; set; }
        public virtual DbSet<registros_ambientales> registros_ambientales { get; set; }
        public virtual DbSet<registros_condiciones> registros_condiciones { get; set; }
        public virtual DbSet<registros_toc> registros_toc { get; set; }
        public virtual DbSet<rel_areas_divisiones> rel_areas_divisiones { get; set; }
        public virtual DbSet<rel_toc> rel_toc { get; set; }
        public virtual DbSet<reporte_configuracion> reporte_configuracion { get; set; }
        public virtual DbSet<reporte_configuracion_tables> reporte_configuracion_tables { get; set; }
        public virtual DbSet<riesgos_identificados> riesgos_identificados { get; set; }
        public virtual DbSet<sub_procesos> sub_procesos { get; set; }
        public virtual DbSet<sub_sub_procesos> sub_sub_procesos { get; set; }
        public virtual DbSet<sucursales> sucursales { get; set; }
        public virtual DbSet<tbl_permisos> tbl_permisos { get; set; }
        public virtual DbSet<tbl_ventanas> tbl_ventanas { get; set; }
        public virtual DbSet<tipos_factores> tipos_factores { get; set; }
        public virtual DbSet<user_preferences> user_preferences { get; set; }
        public virtual DbSet<usuarios> usuarios { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<areas>()
                .Property(e => e.nombre)
                .IsUnicode(false);

            modelBuilder.Entity<areas>()
                .Property(e => e.path_icono)
                .IsUnicode(false);

            modelBuilder.Entity<areas>()
                .Property(e => e.tipo)
                .IsUnicode(false);

            modelBuilder.Entity<areas>()
                .HasMany(e => e.registros_ambientales)
                .WithRequired(e => e.areas)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<areas>()
                .HasMany(e => e.registros_condiciones)
                .WithRequired(e => e.areas)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<areas>()
                .HasMany(e => e.registros_toc)
                .WithRequired(e => e.areas)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<areas>()
                .HasMany(e => e.rel_areas_divisiones)
                .WithRequired(e => e.areas)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<dispositivos>()
                .Property(e => e.token)
                .IsUnicode(false);

            modelBuilder.Entity<dispositivos>()
                .Property(e => e.so)
                .IsUnicode(false);

            modelBuilder.Entity<divisiones>()
                .Property(e => e.descripcion)
                .IsUnicode(false);

            modelBuilder.Entity<divisiones>()
                .HasMany(e => e.rel_toc)
                .WithRequired(e => e.divisiones)
                .HasForeignKey(e => e.idDivision)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<divisiones>()
                .HasMany(e => e.rel_areas_divisiones)
                .WithRequired(e => e.divisiones)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<emailconfig>()
                .Property(e => e.EmailOrigen)
                .IsUnicode(false);

            modelBuilder.Entity<emailconfig>()
                .Property(e => e.Servidor)
                .IsUnicode(false);

            modelBuilder.Entity<emailconfig>()
                .Property(e => e.Puerto)
                .IsUnicode(false);

            modelBuilder.Entity<emailconfig>()
                .Property(e => e.EmailPassword)
                .IsUnicode(false);

            modelBuilder.Entity<emailconfig>()
                .Property(e => e.Error)
                .IsUnicode(false);

            modelBuilder.Entity<estatus>()
                .Property(e => e.nombre)
                .IsUnicode(false);

            modelBuilder.Entity<estatus>()
                .Property(e => e.tipo)
                .IsUnicode(false);

            modelBuilder.Entity<estatus>()
                .HasMany(e => e.areas)
                .WithRequired(e => e.estatus)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<estatus>()
                .HasMany(e => e.factores)
                .WithRequired(e => e.estatus)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<estatus>()
                .HasMany(e => e.origenes_observaciones)
                .WithRequired(e => e.estatus)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<estatus>()
                .HasMany(e => e.procesos)
                .WithRequired(e => e.estatus)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<estatus>()
                .HasMany(e => e.riesgos_identificados)
                .WithRequired(e => e.estatus)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<estatus>()
                .HasMany(e => e.sub_procesos)
                .WithRequired(e => e.estatus)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<estatus>()
                .HasMany(e => e.sub_sub_procesos)
                .WithRequired(e => e.estatus)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<estatus>()
                .HasMany(e => e.usuarios)
                .WithRequired(e => e.estatus)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<factores>()
                .Property(e => e.nombre)
                .IsUnicode(false);

            modelBuilder.Entity<factores>()
                .Property(e => e.path_icono)
                .IsUnicode(false);

            modelBuilder.Entity<factores>()
                .HasMany(e => e.registros_ambientales)
                .WithRequired(e => e.factores)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<factores>()
                .HasMany(e => e.registros_condiciones)
                .WithRequired(e => e.factores)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<factores>()
                .HasMany(e => e.registros_toc)
                .WithRequired(e => e.factores)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<menu_ejecutivo>()
                .Property(e => e.Observaciones)
                .IsUnicode(false);

            modelBuilder.Entity<meses>()
                .Property(e => e.Mes)
                .IsUnicode(false);

            modelBuilder.Entity<origenes_observaciones>()
                .Property(e => e.nombre)
                .IsUnicode(false);

            modelBuilder.Entity<origenes_observaciones>()
                .HasMany(e => e.registros_ambientales)
                .WithRequired(e => e.origenes_observaciones)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<origenes_observaciones>()
                .HasMany(e => e.registros_condiciones)
                .WithRequired(e => e.origenes_observaciones)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<origenes_observaciones>()
                .HasMany(e => e.registros_toc)
                .WithRequired(e => e.origenes_observaciones)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<procesos>()
                .Property(e => e.nombre)
                .IsUnicode(false);

            modelBuilder.Entity<procesos>()
                .HasMany(e => e.rel_toc)
                .WithRequired(e => e.procesos)
                .HasForeignKey(e => e.idProceso)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<puestos>()
                .Property(e => e.clave)
                .IsUnicode(false);

            modelBuilder.Entity<puestos>()
                .Property(e => e.nombre)
                .IsUnicode(false);

            modelBuilder.Entity<regiones>()
                .Property(e => e.nombre)
                .IsUnicode(false);

            modelBuilder.Entity<regiones>()
                .Property(e => e.Region_nombre)
                .IsUnicode(false);

            modelBuilder.Entity<regiones>()
                .HasMany(e => e.rel_toc)
                .WithRequired(e => e.regiones)
                .HasForeignKey(e => e.idRegion)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<registros_ambientales>()
                .Property(e => e.observaciones)
                .IsUnicode(false);

            modelBuilder.Entity<registros_ambientales>()
                .Property(e => e.acciones_a_tomar)
                .IsUnicode(false);

            modelBuilder.Entity<registros_ambientales>()
                .Property(e => e.maquina_equipo)
                .IsUnicode(false);

            modelBuilder.Entity<registros_ambientales>()
                .Property(e => e.fecha_hora_registro)
                .HasPrecision(6);

            modelBuilder.Entity<registros_ambientales>()
                .Property(e => e.adicional_suc)
                .IsUnicode(false);

            modelBuilder.Entity<registros_ambientales>()
                .Property(e => e.fecha_hora_atendido)
                .HasPrecision(6);

            modelBuilder.Entity<registros_ambientales>()
                .Property(e => e.comentarios_atencion)
                .IsUnicode(false);

            modelBuilder.Entity<registros_ambientales>()
                .Property(e => e.token)
                .IsUnicode(false);

            modelBuilder.Entity<registros_condiciones>()
                .Property(e => e.observaciones)
                .IsUnicode(false);

            modelBuilder.Entity<registros_condiciones>()
                .Property(e => e.acciones_a_tomar)
                .IsUnicode(false);

            modelBuilder.Entity<registros_condiciones>()
                .Property(e => e.maquina_equipo_zona)
                .IsUnicode(false);

            modelBuilder.Entity<registros_condiciones>()
                .Property(e => e.fecha_hora_registro)
                .HasPrecision(6);

            modelBuilder.Entity<registros_condiciones>()
                .Property(e => e.adicional_suc)
                .IsUnicode(false);

            modelBuilder.Entity<registros_condiciones>()
                .Property(e => e.fecha_hora_atendido)
                .HasPrecision(6);

            modelBuilder.Entity<registros_condiciones>()
                .Property(e => e.comentarios_atencion)
                .IsUnicode(false);

            modelBuilder.Entity<registros_condiciones>()
                .Property(e => e.token)
                .IsUnicode(false);

            modelBuilder.Entity<registros_toc>()
                .Property(e => e.observaciones)
                .IsUnicode(false);

            modelBuilder.Entity<registros_toc>()
                .Property(e => e.acciones_a_tomar)
                .IsUnicode(false);

            modelBuilder.Entity<registros_toc>()
                .Property(e => e.maquina_equipo)
                .IsUnicode(false);

            modelBuilder.Entity<registros_toc>()
                .Property(e => e.fecha_hora_registro)
                .HasPrecision(6);

            modelBuilder.Entity<registros_toc>()
                .Property(e => e.adicional_suc)
                .IsUnicode(false);

            modelBuilder.Entity<registros_toc>()
                .Property(e => e.token)
                .IsUnicode(false);

            modelBuilder.Entity<reporte_configuracion>()
                .Property(e => e.Texto)
                .IsUnicode(false);

            modelBuilder.Entity<reporte_configuracion>()
                .Property(e => e.Colortext)
                .IsUnicode(false);

            modelBuilder.Entity<reporte_configuracion>()
                .Property(e => e.ColText)
                .IsUnicode(false);

            modelBuilder.Entity<reporte_configuracion>()
                .Property(e => e.RowText)
                .IsUnicode(false);

            modelBuilder.Entity<reporte_configuracion_tables>()
                .Property(e => e.Nombre)
                .IsUnicode(false);

            modelBuilder.Entity<riesgos_identificados>()
                .Property(e => e.nombre)
                .IsUnicode(false);

            modelBuilder.Entity<sub_procesos>()
                .Property(e => e.nombre)
                .IsUnicode(false);

            modelBuilder.Entity<sub_procesos>()
                .HasMany(e => e.rel_toc)
                .WithRequired(e => e.sub_procesos)
                .HasForeignKey(e => e.idSubProceso)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<sub_sub_procesos>()
                .Property(e => e.nombre)
                .IsUnicode(false);

            modelBuilder.Entity<sucursales>()
                .Property(e => e.nombre)
                .IsUnicode(false);

            modelBuilder.Entity<sucursales>()
                .Property(e => e.clave)
                .IsUnicode(false);

            modelBuilder.Entity<sucursales>()
                .Property(e => e.pais)
                .IsUnicode(false);

            modelBuilder.Entity<sucursales>()
                .Property(e => e.compañia)
                .IsUnicode(false);

            modelBuilder.Entity<sucursales>()
                .Property(e => e.region)
                .IsUnicode(false);

            modelBuilder.Entity<sucursales>()
                .HasMany(e => e.registros_ambientales)
                .WithRequired(e => e.sucursales)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<sucursales>()
                .HasMany(e => e.registros_condiciones)
                .WithRequired(e => e.sucursales)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<sucursales>()
                .HasMany(e => e.registros_toc)
                .WithRequired(e => e.sucursales)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<sucursales>()
                .HasMany(e => e.rel_toc)
                .WithRequired(e => e.sucursales)
                .HasForeignKey(e => e.idSucursal)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<tbl_ventanas>()
                .Property(e => e.Nombre)
                .IsUnicode(false);

            modelBuilder.Entity<tipos_factores>()
                .Property(e => e.nombre)
                .IsUnicode(false);

            modelBuilder.Entity<tipos_factores>()
                .Property(e => e.desMensaje1)
                .IsUnicode(false);

            modelBuilder.Entity<tipos_factores>()
                .Property(e => e.desMensaje2)
                .IsUnicode(false);

            modelBuilder.Entity<tipos_factores>()
                .HasMany(e => e.factores)
                .WithRequired(e => e.tipos_factores)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<usuarios>()
                .Property(e => e.clave)
                .IsUnicode(false);

            modelBuilder.Entity<usuarios>()
                .Property(e => e.nombre)
                .IsUnicode(false);

            modelBuilder.Entity<usuarios>()
                .Property(e => e.clave_sucursal)
                .IsUnicode(false);

            modelBuilder.Entity<usuarios>()
                .Property(e => e.nombre_sucursal)
                .IsUnicode(false);

            modelBuilder.Entity<usuarios>()
                .Property(e => e.clave_puesto)
                .IsUnicode(false);

            modelBuilder.Entity<usuarios>()
                .Property(e => e.nombre_puesto)
                .IsUnicode(false);

            modelBuilder.Entity<usuarios>()
                .Property(e => e.departamento)
                .IsUnicode(false);

            modelBuilder.Entity<usuarios>()
                .Property(e => e.proceso)
                .IsUnicode(false);

            modelBuilder.Entity<usuarios>()
                .Property(e => e.sub_proceso)
                .IsUnicode(false);

            modelBuilder.Entity<usuarios>()
                .Property(e => e.sub_sub_proceso)
                .IsUnicode(false);

            modelBuilder.Entity<usuarios>()
                .Property(e => e.compañia)
                .IsUnicode(false);

            modelBuilder.Entity<usuarios>()
                .Property(e => e.region)
                .IsUnicode(false);

            modelBuilder.Entity<usuarios>()
                .Property(e => e.pais)
                .IsUnicode(false);

            modelBuilder.Entity<usuarios>()
                .Property(e => e.Correo)
                .IsUnicode(false);

            modelBuilder.Entity<usuarios>()
                .Property(e => e.Password)
                .IsUnicode(false);

            modelBuilder.Entity<usuarios>()
                .HasMany(e => e.registros_ambientales)
                .WithRequired(e => e.usuarios)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<usuarios>()
                .HasMany(e => e.registros_condiciones)
                .WithRequired(e => e.usuarios)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<usuarios>()
                .HasMany(e => e.registros_toc)
                .WithRequired(e => e.usuarios)
                .WillCascadeOnDelete(false);
        }
    }
}
