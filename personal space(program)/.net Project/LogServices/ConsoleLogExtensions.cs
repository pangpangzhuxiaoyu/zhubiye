using System;
using Microsoft.Extensions.DependencyInjection;
using System.Collections.Generic;
using System.Text;
using LogServices;

namespace Microsoft.Extensions.DependencyInjection
{
    public static class ConsoleLogExtensions
    {
        public static void AddConosoleLog(this ServiceCollection service) 
        {
            service.AddScoped<ILogProvider, ConsoleLogProvider>();
        }
    }
}
