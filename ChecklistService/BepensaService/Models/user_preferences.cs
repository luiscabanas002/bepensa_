namespace BepensaService.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("bepensa.user_preferences")]
    public partial class user_preferences
    {
        [Key]
        public int idUser_preferences { get; set; }

        public int? Id_User { get; set; }

        public int? IDPreference { get; set; }

        public int? CountPreference { get; set; }

        public int? Type_view { get; set; }

        public int? Type_preference { get; set; }
    }
}
