namespace BepensaService.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("bepensa.riesgos_identificados")]
    public partial class riesgos_identificados
    {
        [Key]
        public int id_riesgo_identificado { get; set; }

        [Required]
        [StringLength(1500)]
        public string nombre { get; set; }

        public int id_estatus { get; set; }

        public virtual estatus estatus { get; set; }
    }
}
