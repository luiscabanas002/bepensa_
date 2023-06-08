namespace BepensaService.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("bepensa.menu_ejecutivo")]
    public partial class menu_ejecutivo
    {
        [Key]
        [Column(TypeName = "uint")]
        public long ID_ejecutivo { get; set; }

        [Required]
        [StringLength(45)]
        public string Observaciones { get; set; }
    }
}
