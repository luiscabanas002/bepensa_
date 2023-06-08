namespace BepensaService.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("bepensa.tbl_ventanas")]
    public partial class tbl_ventanas
    {
        [Key]
        public int idtbl_ventanas { get; set; }

        [StringLength(45)]
        public string Nombre { get; set; }
    }
}
