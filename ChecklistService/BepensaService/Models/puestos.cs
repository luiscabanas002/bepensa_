namespace BepensaService.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("bepensa.puestos")]
    public partial class puestos
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public puestos()
        {
            usuarios = new HashSet<usuarios>();
        }

        [Key]
        public long id_puesto { get; set; }

        [StringLength(255)]
        public string clave { get; set; }

        [StringLength(255)]
        public string nombre { get; set; }

        public int? id_estatus { get; set; }

        public virtual estatus estatus { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<usuarios> usuarios { get; set; }
    }
}
