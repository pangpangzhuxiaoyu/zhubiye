using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using NLog.Extensions.Logging;
using Serilog.Events;
using Serilog;

namespace lOGGING
{
    internal class Program
    {
        /*这次学的是两个log框架，一个是NLog一个是SeriLog
         * NLog相比于之前的log4net，它与.NETCore集成的更好
         * 而SeriLog是另一个门派，它是结构化日志，输出的日志是json结构的
         */
        static void Main(string[] args)
        {
            ServiceCollection services = new ServiceCollection();
            
            services.AddLogging(logBuilder =>
            {
                ////输入到控制台
                //logBuilder.AddConsole();
                //logBuilder.AddNLog();
                //使用SeriLog
                Log.Logger = new LoggerConfiguration()
                .MinimumLevel.Debug()
                .WriteTo.Console(restrictedToMinimumLevel: LogEventLevel.Debug)
                .CreateLogger();
                logBuilder.AddSerilog();

            });
            services.AddScoped<LogText>();
            using (var sp = services.BuildServiceProvider())
            {
                var test1 = sp.GetRequiredService<LogText>();
                test1.Test();
            }

            

        }
    }
}