namespace BepensaService.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("bepensa.reporte_configuracion")]
    public partial class reporte_configuracion
    {
        [Key]
        public int idReporte_configuracion { get; set; }

        public int? numeroreporte { get; set; }

        [StringLength(1073741823)]
        public string Texto { get; set; }

        [StringLength(10)]
        public string Colortext { get; set; }

        [StringLength(45)]
        public string ColText { get; set; }

        [StringLength(45)]
        public string RowText { get; set; }

        public int? align { get; set; }

        public float? FontSize { get; set; }

        public int? SizeX { get; set; }

        public int? SizeY { get; set; }

        public int? Tipo { get; set; }

        public int? Xposition { get; set; }

        public int? Yposition { get; set; }
    }
}
