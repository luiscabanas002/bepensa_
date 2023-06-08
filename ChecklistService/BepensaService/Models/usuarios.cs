namespace BepensaService.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("bepensa.usuarios")]
    public partial class usuarios
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public usuarios()
        {
            registros_ambientales = new HashSet<registros_ambientales>();
            registros_condiciones = new HashSet<registros_condiciones>();
            registros_toc = new HashSet<registros_toc>();
        }

        [Key]
        public long id_usuario { get; set; }

        [Required]
        [StringLength(1500)]
        public string clave { get; set; }

        [Required]
        [StringLength(5000)]
        public string nombre { get; set; }

        [Required]
        [StringLength(255)]
        public string clave_sucursal { get; set; }

        [Required]
        [StringLength(1000)]
        public string nombre_sucursal { get; set; }

        [Required]
        [StringLength(255)]
        public string clave_puesto { get; set; }

        [Required]
        [StringLength(255)]
        public string nombre_puesto { get; set; }

        [Required]
        [StringLength(1000)]
        public string departamento { get; set; }

        [StringLength(1000)]
        public string proceso { get; set; }

        [StringLength(1000)]
        public string sub_proceso { get; set; }

        public int id_estatus { get; set; }

        [StringLength(1000)]
        public string sub_sub_proceso { get; set; }

        [StringLength(1000)]
        public string compañia { get; set; }

        [StringLength(1000)]
        public string region { get; set; }

        [StringLength(1000)]
        public string pais { get; set; }

        public int? id_sucursal { get; set; }

        public long? id_puesto { get; set; }

        public int? id_proceso { get; set; }

        public int? id_sub_proceso { get; set; }

        public int? id_sub_sub_proceso { get; set; }

        [Column(TypeName = "uint")]
        public long Id_ejecutivo { get; set; }

        [StringLength(100)]
        public string Correo { get; set; }

        [StringLength(100)]
        public string Password { get; set; }

        [Column(TypeName = "uint")]
        public long id_region { get; set; }

        public DateTime? Reg_timestamp { get; set; }

        public int? Id_division { get; set; }

        public int? app { get; set; }

        public int TipoUsuario { get; set; }

        public virtual estatus estatus { get; set; }

        public virtual procesos procesos { get; set; }

        public virtual puestos puestos { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<registros_ambientales> registros_ambientales { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<registros_condiciones> registros_condiciones { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<registros_toc> registros_toc { get; set; }

        public virtual sub_procesos sub_procesos { get; set; }

        public virtual sub_sub_procesos sub_sub_procesos { get; set; }

        public virtual sucursales sucursales { get; set; }
    }
}
