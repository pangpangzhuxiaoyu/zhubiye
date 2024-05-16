using System.Text;

namespace Ansync
{
    internal class Program
    {
        static async Task Main(string[] args)
        {
            #region   learn
            //string filename = @"D:\\gitcode\\zhubiye\\personal space(program)\\.net Project\\Thread\\日本語.txt";
            ////如果File.WriteAllTextAsync前面不加await这样就会报错
            ////因为writerAllTEXT为读栈式写入，在写入时不能读取
            ////而不加await会直接进行下面的语句导致报错
            //StringBuilder stringBuilder = new StringBuilder();
            //for (int i = 0; i < 100000; i++)
            //{
            //    stringBuilder.Append("666");
            //}
            //await File.WriteAllTextAsync(filename,"HHH");
            //string s= await File.ReadAllTextAsync(filename);
            //Console.WriteLine(s);
            #endregion

            #region lambda表达式中异步的使用    
            //string filename = @"D:\\gitcode\\zhubiye\\personal space(program)\\.net Project\\Thread\\日本語.txt";
            //ThreadPool.QueueUserWorkItem(async (obj) =>
            //{
            //    while (true)
            //    {
            //        await File.WriteAllTextAsync(filename, "HHH");
            //        Console.WriteLine("666");
            //    }
            //});
            #endregion

            #region  async和await的内部原理
            ///*
            // * 这段建议将dll反编译看一下，大体概括就是，在工作的时候
            // * await并没有一直wait，而是执行其他的步骤，等await代码执行完成后继续进行
            // */
            //string filename = @"D:\\gitcode\\zhubiye\\personal space(program)\\.net Project\\Thread\\日本語.txt";
            //using (HttpClient http=new HttpClient())
            //{
            //    await http.GetStringAsync("https://www.baidu.com");
            //    string txt = "hi zhu";
            //    await File.WriteAllTextAsync(txt,filename);
            //    string context=await File.ReadAllTextAsync(filename);
            //    Console.WriteLine(context);
            //}

            #endregion

            #region  async和await的内部原理之线程切换
            /*
             * 异步调用前后可能用到的都不是一个线程，在等待的过程中，线程会返回线程池
             * 在等待结束后，从线程池取出空闲的线程
             */
            //string filename = @"D:\\gitcode\\zhubiye\\personal space(program)\\.net Project\\Thread\\日本語.txt";
            //Console.WriteLine(Thread.CurrentThread.ManagedThreadId);
            //StringBuilder sb = new StringBuilder();
            //for (int i = 0; i < 10000; i++)
            //{
            //    sb.Append("KKK");
            //}
            //await File.WriteAllTextAsync(filename, sb.ToString());
            //Console.WriteLine(Thread.CurrentThread.ManagedThreadId);
            #endregion

            #region  自己写方法的时候有时候可以不用写asaync
            /*
             * asuync方法的缺点：
             *      异步方法会生成一个类，运行效率没有普通方法高
             *      可能会占用多个线程，所以自己写方法的时候
             *      可以不用尽量不用
             */
            //string s = await GetString(0);
            //Console.WriteLine(s);
            #endregion

            #region  异步编程的时候不要用Thread.Sleep();
            /*
             * 因为Thread.Sleep()会阻塞主线程，不利于并发
             * 要使用await Task.Delay(3000)
             * 因为异步方法编译完成后是分多模块执行的，这个方法只停止当前的模块（先这么理解）
             */
            //await Task.Delay(3000);
            #endregion

            #region  CancellationToken（用户客户端取消的时候终止服务端代码继续执行）
            /*
             * 对比一下两个方法的区别（DownloadAsync···Download2Async）
             * 一个是自己手动去终止
             * 另一个是.NET内置方法终止
             * 前者在执行中可能就终止了
             * 而手动的话必须要方法执行完成才终止，
             * 也就是阻止了下一次的循环而不是直接终止
             */
            //CancellationTokenSource cancellationToken = new CancellationTokenSource(3000);
            ////CancellationToken是一个结构体，需要借助CancellationTokenSource来创建
            //CancellationToken ct = cancellationToken.Token;
            //string url = $"https://www.baidu.com"; 
            //await DownloadAsync(url,100,ct);
            //await Download2Async(url, 100, ct);

            #endregion

            #region  yield return和异步一起使用（非常用）
            /*
             * yield和异步原理其实差不多，反编译以后可以看出
             * 这是C#的一个语法糖，利用状态机来进行调度
             * C#之前的语法中不允许yield return和异步一起使用
             * 但在C#8之后引入了IAsyncEnumerable之后，就可以一起使用了
             * 具体用法如下
             */
            
            //非C#8.0
            foreach (var item in Test1())
            {
                Console.WriteLine(item);
            }

            //yes
            await foreach(var item in Test2())
            {
                Console.WriteLine(item);
            }


            #endregion

        }
        static async IAsyncEnumerable<string> Test2()
        {
            yield return "1";
            yield return "2";
            yield return "3";
        }
        static IEnumerable<string> Test1()
        {
            yield return "1";
            yield return "2";
            yield return "3";
        }
        

        static async Task DownloadAsync(string url, int n, CancellationToken cancellationToken)
        {
            using (HttpClient httpClient =new HttpClient())
            {
                for (int i = 0; i < n; i++)
                {
                    string client = await httpClient.GetStringAsync(url);
                    Console.WriteLine($"{client}");
                    if (cancellationToken.IsCancellationRequested)
                    {
                        Console.WriteLine("请求被取消");
                        break;
                    }
                }
                
            }
        }
        static async Task Download2Async(string url, int n, CancellationToken cancellationToken)
        {
            using (HttpClient httpClient = new HttpClient())
            {
                for (int i = 0; i < n; i++)
                {
                    string client = await httpClient.GetStringAsync(url,cancellationToken);
                    Console.WriteLine($"{client}");
                    //if (cancellationToken.IsCancellationRequested)
                    //{
                    //    Console.WriteLine("请求被取消");
                    //    break;
                    //}
                }

            }
        }



        static Task<string> GetString(int num)
        {
            string filename = @"D:\\gitcode\\zhubiye\\personal space(program)\\.net Project\\Thread\\日本語.txt";
            switch (num)
            {
                case 0:
                    return File.ReadAllTextAsync(filename);
                case 1:
                    return File.ReadAllTextAsync(filename);
                default:
                    throw new Exception();
            }



        }





    }
}