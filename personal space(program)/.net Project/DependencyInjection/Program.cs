using ConfigService;
using LogServices;
using Microsoft.Extensions.DependencyInjection;
using MileService;

namespace DependencyInjection
{
    internal class Program
    {
        /*
         * 总结
         * 不要在长生命周期的对象中引用比他短生命周期的对象。
         * 生命周期的选择：如果这个类没有状态（没有属性等），建议使用Singleton，如果
         *                 类有状态，而且有scope控制，建议为Scoped，因为通常这种控制下的代码都是运行在
         *                 同一个线程中的，并没有并发修改的问题
         *一些使用细节请看下面相关代码
         */


        static void Main(string[] args)
        {//降低模块之间的耦合
            #region  服务的生命周期

            //ServiceCollection services = new ServiceCollection();
            ////瞬时服务（用完对象再次获取的时候就是另一个）
            //services.AddTransient<TestServiceImpl>();
            //using (ServiceProvider sp = services.BuildServiceProvider())
            //{
            //    TestServiceImpl testServiceImpl = sp.GetService<TestServiceImpl>();
            //    TestServiceImpl testServiceImpl2 = sp.GetService<TestServiceImpl>();
            //    Console.WriteLine(Object.ReferenceEquals(testServiceImpl, testServiceImpl2));


            //}
            ////单例（用完之后再次获取还是同一个）
            //services.AddSingleton<TestServiceImpl>();
            //using (ServiceProvider sp = services.BuildServiceProvider())
            //{
            //    TestServiceImpl testServiceImpl = sp.GetService<TestServiceImpl>();
            //    TestServiceImpl testServiceImpl2 = sp.GetService<TestServiceImpl>();
            //    Console.WriteLine(Object.ReferenceEquals(testServiceImpl, testServiceImpl2));


            //}

            ////指定范围服务(服务的范围程序员自己指定，使用方法如下：)
            //services.AddScoped<ITestService, TestServiceImpl>();
            //using (ServiceProvider sp = services.BuildServiceProvider())
            //{
            //    using (IServiceScope Is = sp.CreateScope())
            //    {
            //        TestServiceImpl testServiceImpl = Is.ServiceProvider.GetService<TestServiceImpl>();
            //        TestServiceImpl testServiceImpl2 = Is.ServiceProvider.GetService<TestServiceImpl>();
            //        Console.WriteLine(Object.ReferenceEquals(testServiceImpl, testServiceImpl2));
            //    }
            //    using (IServiceScope Is2 = sp.CreateScope())
            //    {
            //        //杨中科真帅！！
            //        TestServiceImpl testServiceImpl = Is2.ServiceProvider.GetService<TestServiceImpl>();
            //        TestServiceImpl testServiceImpl2 = Is2.ServiceProvider.GetService<TestServiceImpl>();
            //        Console.WriteLine(Object.ReferenceEquals(testServiceImpl, testServiceImpl2));
            //    }

            //}
            #endregion

            #region  服务的使用细节
            //ServiceCollection service6 = new ServiceCollection();
            ////注册服务（这部分一般是框架写的）ITestService为服务类型，TestServiceImpl为实现类型
            ////注册是有先后顺序的
            //service6.AddTransient<ITestService,TestServiceImpl>();
            //service6.AddTransient<ITestService, TestServiceImplw>();
            //using (ServiceProvider sp= service6.BuildServiceProvider())
            //{
            //    //这种写法就是错的，因为找不到TestServiceImpl这个服务类型，注册的时候ITestService为服务类型，而TestServiceImpl为实现类型
            //    //这个方法找不到的话会返回null
            //    sp.GetService<TestServiceImpl>();
            //    //这个方法找不到的话直接报错
            //    sp.GetRequiredService<TestServiceImpl>();
            //    //机票的多个服务类型
            //    IEnumerable<ITestService> ts= sp.GetServices<ITestService>();
            //}

            #endregion

            #region 框架编写案例（不是平时写代码时候用的，偏一点点底层）
            ServiceCollection services = new ServiceCollection();
            services.AddScoped<IConfigService, EnvVarConfigService>();
            services.AddScoped<IMailService, MailService>();
            //services.AddScoped<ILogProvider, ConsoleLogProvider>();
            //这个是自己写的扩展方法
            services.AddConosoleLog();
            using (var sp = services.BuildServiceProvider())
            {
                var mailService = sp.GetRequiredService<IMailService>();
                mailService.Send("Hello", "he.zhu@dxc.com", "welcome!");
            }
            Console.ReadLine();

            #endregion



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