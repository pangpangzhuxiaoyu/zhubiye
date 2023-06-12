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
            services.AddOptions().Configure<Config>();






        }


    }

    public class Config
    {
        private string name;

        private int age;

        public string Name { get => name; set => name = value; }
        public int Age { get => age; set => age = value; }

        public class haha
        {
            private string cc;

            public string Cc { get => cc; set => cc = value; }
        }


    }
}

