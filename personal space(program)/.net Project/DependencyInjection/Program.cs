using ConfigService;
using LogServices;
using Microsoft.Extensions.DependencyInjection;
using MileService;

namespace DependencyInjection
{
    internal class Program
    {
        static void Main(string[] args)
        {//降低模块之间的耦合
            #region  服务定位器（学习用）
            /*
            ServiceCollection services = new ServiceCollection();
            //瞬时服务
            //services.AddTransient<TestServiceImpl>();
            //指定范围服务
            services.AddScoped<ITestService, TestServiceImpl>();
            services.AddScoped<ITestService, TestServiceImplw>();
            using (ServiceProvider sp = services.BuildServiceProvider())
            {
                IEnumerable<ITestService> tests = sp.GetServices<ITestService>();
                foreach (ITestService test in tests)
                {
                    Console.WriteLine(test.GetType());
                }

                using (IServiceScope Is = sp.CreateScope())
                {
                    TestServiceImpl testServiceImpl = Is.ServiceProvider.GetService<TestServiceImpl>();
                    TestServiceImpl testServiceImpl2 = Is.ServiceProvider.GetService<TestServiceImpl>();
                    Console.WriteLine(Object.ReferenceEquals(testServiceImpl, testServiceImpl2));
                }
                using (IServiceScope Is2 = sp.CreateScope())
                {
                    //杨中科真帅！！
                    TestServiceImpl testServiceImpl = Is2.ServiceProvider.GetService<TestServiceImpl>();
                    TestServiceImpl testServiceImpl2 = Is2.ServiceProvider.GetService<TestServiceImpl>();
                    Console.WriteLine(Object.ReferenceEquals(testServiceImpl, testServiceImpl2));
                }

            }*/
            #endregion

            ServiceCollection services = new ServiceCollection();
            services.AddScoped<IConfigService, EnvVarConfigService>();
            services.AddScoped<IMailService, MailService>();
            //services.AddScoped<ILogProvider, ConsoleLogProvider>();
            services.AddConosoleLog();
            using (var sp = services.BuildServiceProvider())
            {
                var mailService = sp.GetRequiredService<IMailService>();
                mailService.Send("Hello","he.zhu@dxc.com","welcome!");
            }
            Console.ReadLine(); 





        }
    }





    internal class TestServiceImpl : ITestService
    {
    }
    internal class TestServiceImplw : ITestService
    {
    }
    interface ITestService
    {

    }
}