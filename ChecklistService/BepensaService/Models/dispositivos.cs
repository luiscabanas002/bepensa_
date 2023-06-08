namespace BepensaService.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("bepensa.dispositivos")]
    public partial class dispositivos
    {
        [Key]
        public long id_dispositivo { get; set; }

        [Required]
        [StringLength(1073741823)]
        public string token { get; set; }

        [Required]
        [StringLength(255)]
        public string so { get; set; }
    }
}
