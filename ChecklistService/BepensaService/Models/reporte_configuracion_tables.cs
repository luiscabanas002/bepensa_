namespace BepensaService.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("bepensa.reporte_configuracion_tables")]
    public partial class reporte_configuracion_tables
    {
        [Key]
        public int idReporte_configuracion_tables { get; set; }

        [StringLength(100)]
        public string Nombre { get; set; }

        public int? IdReporte { get; set; }

        public int? NoColumnas { get; set; }

        public int? XPosition { get; set; }

        public int? YPosition { get; set; }

        public int? NoTable { get; set; }
    }
}
