using System;
using System.Collections.Generic;
using System.Text;

namespace ConfigService
{
    public class EnvVarConfigService : IConfigService
    {
        public string GetValue(string name)
        {
            return Environment.GetEnvironmentVariable(name);
        }
    }
}
