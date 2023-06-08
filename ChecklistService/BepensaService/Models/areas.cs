namespace BepensaService.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("bepensa.areas")]
    public partial class areas
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public areas()
        {
            registros_ambientales = new HashSet<registros_ambientales>();
            registros_condiciones = new HashSet<registros_condiciones>();
            registros_toc = new HashSet<registros_toc>();
            rel_areas_divisiones = new HashSet<rel_areas_divisiones>();
        }

        [Key]
        public int id_area { get; set; }

        [Required]
        [StringLength(1500)]
        public string nombre { get; set; }

        public int id_estatus { get; set; }

        [Column(TypeName = "text")]
        [Required]
        [StringLength(65535)]
        public string path_icono { get; set; }

        public int num_posicion { get; set; }

        [StringLength(1500)]
        public string tipo { get; set; }

        public virtual estatus estatus { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<registros_ambientales> registros_ambientales { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<registros_condiciones> registros_condiciones { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<registros_toc> registros_toc { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<rel_areas_divisiones> rel_areas_divisiones { get; set; }
    }
}
