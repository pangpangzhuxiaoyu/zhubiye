using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Hi.Pages.Modles;

namespace Hi.Data
{
    public class HiContext : DbContext
    {
        public HiContext (DbContextOptions<HiContext> options)
            : base(options)
        {
        }

        public DbSet<Movie> Movie { get; set; } = default!;
    }
}
