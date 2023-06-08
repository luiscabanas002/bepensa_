namespace BepensaService.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("bepensa.emailconfig")]
    public partial class emailconfig
    {
        public int Id { get; set; }

        [Required]
        [StringLength(250)]
        public string EmailOrigen { get; set; }

        [Required]
        [StringLength(250)]
        public string Servidor { get; set; }

        [Required]
        [StringLength(50)]
        public string Puerto { get; set; }

        [Required]
        [StringLength(50)]
        public string EmailPassword { get; set; }

        public int MaximoEnvios { get; set; }

        public int Enviados { get; set; }

        public bool RegStatus { get; set; }

        [StringLength(500)]
        public string Error { get; set; }

        public DateTime? UpdatedAt { get; set; }
    }
}
