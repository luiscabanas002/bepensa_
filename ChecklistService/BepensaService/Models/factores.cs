namespace BepensaService.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("bepensa.factores")]
    public partial class factores
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public factores()
        {
            registros_ambientales = new HashSet<registros_ambientales>();
            registros_condiciones = new HashSet<registros_condiciones>();
            registros_toc = new HashSet<registros_toc>();
        }

        [Key]
        public int id_factor { get; set; }

        public int id_tipo_factor { get; set; }

        [Required]
        [StringLength(1073741823)]
        public string nombre { get; set; }

        public int id_estatus { get; set; }

        [Column(TypeName = "text")]
        [Required]
        [StringLength(65535)]
        public string path_icono { get; set; }

        public int num_posicion { get; set; }

        public virtual estatus estatus { get; set; }

        public virtual tipos_factores tipos_factores { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<registros_ambientales> registros_ambientales { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<registros_condiciones> registros_condiciones { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<registros_toc> registros_toc { get; set; }
    }
}
