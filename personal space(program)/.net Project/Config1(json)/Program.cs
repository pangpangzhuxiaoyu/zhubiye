using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

namespace Config1_json_
{
    internal class Program
    {
        static void Main(string[] args)
        {
            #region 传统读取方法
            //ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
            //configurationBuilder.AddJsonFile("config.json",optional:true,reloadOnChange:true);
            //IConfigurationRoot configurationRoot = configurationBuilder.Build();
            //string name = configurationRoot["name"];
            //string hh = configurationRoot.GetSection("hh:cc").Value;
            //Console.WriteLine($"{name},{hh}");
            //Console.ReadLine();
            #endregion
            ServiceCollection services = new ServiceCollection();
            services.AddScoped<DiRead>();
            ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
            //configurationBuilder.AddJsonFile("config.json", optional: true, reloadOnChange: true);
            //configurationBuilder.AddEnvironmentVariables();
            //configurationBuilder.AddCommandLine(args);
            IConfigurationRoot configurationRoot = configurationBuilder.Build();
            services.AddOptions()
                .Configure<Config>(e => configurationRoot.GetSection("Config"));

            using (var sp = services.BuildServiceProvider())
            {
                var c = sp.GetService<DiRead>();
                c.Test();

            }

        }


    }

    public class Config
    {
        public string Name { get; set; }

        public int Age { get; set; }
        public Hh Hh { get; set; }

        //public hh hh { get => hh_; set => hh_ = value; }
    }
    public class Hh
    {
        public string Cc { get; set; }

    }
}

