namespace BepensaService.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("bepensa.origenes_observaciones")]
    public partial class origenes_observaciones
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public origenes_observaciones()
        {
            registros_ambientales = new HashSet<registros_ambientales>();
            registros_condiciones = new HashSet<registros_condiciones>();
            registros_toc = new HashSet<registros_toc>();
        }

        [Key]
        public int id_origen_observacion { get; set; }

        [Required]
        [StringLength(2000)]
        public string nombre { get; set; }

        public int id_estatus { get; set; }

        public virtual estatus estatus { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<registros_ambientales> registros_ambientales { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<registros_condiciones> registros_condiciones { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<registros_toc> registros_toc { get; set; }
    }
}
