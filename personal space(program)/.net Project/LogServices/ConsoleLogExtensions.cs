using System;
using Microsoft.Extensions.DependencyInjection;
using System.Collections.Generic;
using System.Text;
using LogServices;

//扩展方法，直接引用现有的命名空间，这样可以避免引用多个命名红箭
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
