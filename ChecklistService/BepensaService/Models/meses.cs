namespace BepensaService.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("bepensa.meses")]
    public partial class meses
    {
        [Key]
        public int idMes { get; set; }

        public int? NoMes { get; set; }

        [StringLength(50)]
        public string Mes { get; set; }
    }
}
