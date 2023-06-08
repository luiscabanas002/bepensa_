namespace BepensaService.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("bepensa.sub_procesos")]
    public partial class sub_procesos
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public sub_procesos()
        {
            rel_toc = new HashSet<rel_toc>();
            usuarios = new HashSet<usuarios>();
        }

        [Key]
        public int id_sub_proceso { get; set; }

        [Required]
        [StringLength(255)]
        public string nombre { get; set; }

        public int id_estatus { get; set; }

        public int? id_proceso { get; set; }

        public virtual estatus estatus { get; set; }

        public virtual procesos procesos { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<rel_toc> rel_toc { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<usuarios> usuarios { get; set; }
    }
}
