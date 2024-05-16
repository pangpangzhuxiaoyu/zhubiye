using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace ConfigService
{
    internal class IniFileConfigService : IConfigService
    {
        public string FilePath { get; set; }
        public string GetValue(string name)
        {
            var kv = File.ReadAllLines(FilePath).Select(s=>new { Name = s.Split('=')[0], Value = s.Split('=')[1] })
                .SingleOrDefault(kv=>kv.Name==name);
            return kv?.Value ;
        }
    }
}
