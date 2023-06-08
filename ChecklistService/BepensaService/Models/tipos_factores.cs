namespace BepensaService.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("bepensa.tipos_factores")]
    public partial class tipos_factores
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public tipos_factores()
        {
            factores = new HashSet<factores>();
        }

        [Key]
        public int id_tipo_factor { get; set; }

        [Required]
        [StringLength(1500)]
        public string nombre { get; set; }

        public int? id_estatus { get; set; }

        [StringLength(1073741823)]
        public string desMensaje1 { get; set; }

        [StringLength(1073741823)]
        public string desMensaje2 { get; set; }

        public virtual estatus estatus { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<factores> factores { get; set; }
    }
}
