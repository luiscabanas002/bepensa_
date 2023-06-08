namespace BepensaService.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("bepensa.rel_areas_divisiones")]
    public partial class rel_areas_divisiones
    {
        [Key]
        public int id_rel_area_division { get; set; }

        public int id_area { get; set; }

        public int id_division { get; set; }

        public virtual areas areas { get; set; }

        public virtual divisiones divisiones { get; set; }
    }
}
