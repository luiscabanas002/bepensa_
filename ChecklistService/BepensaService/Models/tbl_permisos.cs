namespace BepensaService.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("bepensa.tbl_permisos")]
    public partial class tbl_permisos
    {
        [Key]
        public int idtbl_permisos { get; set; }

        public int? IdUsuario { get; set; }

        public int? IdPermiso { get; set; }
    }
}
