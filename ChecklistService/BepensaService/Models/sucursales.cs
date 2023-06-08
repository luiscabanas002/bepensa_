namespace BepensaService.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("bepensa.sucursales")]
    public partial class sucursales
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public sucursales()
        {
            registros_ambientales = new HashSet<registros_ambientales>();
            registros_condiciones = new HashSet<registros_condiciones>();
            registros_toc = new HashSet<registros_toc>();
            rel_toc = new HashSet<rel_toc>();
            usuarios = new HashSet<usuarios>();
        }

        [Key]
        public int id_sucursal { get; set; }

        [Column(TypeName = "text")]
        [Required]
        [StringLength(65535)]
        public string nombre { get; set; }

        [Column(TypeName = "text")]
        [StringLength(65535)]
        public string clave { get; set; }

        [Column(TypeName = "text")]
        [StringLength(65535)]
        public string pais { get; set; }

        [Column(TypeName = "text")]
        [StringLength(65535)]
        public string compañia { get; set; }

        public int? id_estatus { get; set; }

        public long? id_region { get; set; }

        [StringLength(45)]
        public string region { get; set; }

        public virtual estatus estatus { get; set; }

        public virtual regiones regiones { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<registros_ambientales> registros_ambientales { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<registros_condiciones> registros_condiciones { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<registros_toc> registros_toc { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<rel_toc> rel_toc { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<usuarios> usuarios { get; set; }
    }
}
