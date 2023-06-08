namespace BepensaService.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("bepensa.registros_ambientales")]
    public partial class registros_ambientales
    {
        [Key]
        public long id_registro_ambiental { get; set; }

        public long id_usuario { get; set; }

        public int id_area { get; set; }

        public int id_factor { get; set; }

        public int id_origen_observacion { get; set; }

        public int id_sucursal { get; set; }

        [StringLength(1073741823)]
        public string observaciones { get; set; }

        [StringLength(1073741823)]
        public string acciones_a_tomar { get; set; }

        [StringLength(5000)]
        public string maquina_equipo { get; set; }

        public DateTime fecha_hora_registro { get; set; }

        [Column(TypeName = "uint")]
        public long id_division { get; set; }

        [Required]
        [StringLength(100)]
        public string adicional_suc { get; set; }

        [Column(TypeName = "bit")]
        public bool atendido { get; set; }

        public DateTime? fecha_hora_atendido { get; set; }

        [StringLength(1073741823)]
        public string comentarios_atencion { get; set; }

        [StringLength(1073741823)]
        public string token { get; set; }

        public virtual areas areas { get; set; }

        public virtual factores factores { get; set; }

        public virtual origenes_observaciones origenes_observaciones { get; set; }

        public virtual sucursales sucursales { get; set; }

        public virtual usuarios usuarios { get; set; }
    }
}
