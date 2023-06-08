namespace BepensaService.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("bepensa.rel_toc")]
    public partial class rel_toc
    {
        [Key]
        public long idRelToc { get; set; }

        public int idDivision { get; set; }

        public int idProceso { get; set; }

        public int idSubProceso { get; set; }

        public long idRegion { get; set; }

        public int idSucursal { get; set; }

        public virtual divisiones divisiones { get; set; }

        public virtual procesos procesos { get; set; }

        public virtual regiones regiones { get; set; }

        public virtual sub_procesos sub_procesos { get; set; }

        public virtual sucursales sucursales { get; set; }
    }
}
