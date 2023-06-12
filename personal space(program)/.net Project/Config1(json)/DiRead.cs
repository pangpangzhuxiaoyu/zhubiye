using Microsoft.Extensions.Options;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Config1_json_
{
    internal class DiRead
    {
        public readonly IOptionsSnapshot<Config> options;
        public DiRead(IOptionsSnapshot<Config> options)
        {
            this.options=options;
        }

        public void Test()
        {
            Console.WriteLine($"{options.Value.Age}");
            Console.WriteLine($"{options.Value.Name}");
        }

    }
}
