namespace BepensaService.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("bepensa.divisiones")]
    public partial class divisiones
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public divisiones()
        {
            rel_toc = new HashSet<rel_toc>();
            rel_areas_divisiones = new HashSet<rel_areas_divisiones>();
        }

        [Key]
        public int id_division { get; set; }

        [Required]
        [StringLength(45)]
        public string descripcion { get; set; }

        [Column(TypeName = "uint")]
        public long aplica_codigo { get; set; }

        [Column(TypeName = "uint")]
        public long aplica_sucursal { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<rel_toc> rel_toc { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<rel_areas_divisiones> rel_areas_divisiones { get; set; }
    }
}
