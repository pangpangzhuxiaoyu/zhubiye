using System.Collections;
using System.Collections.Generic;
using System.Runtime.CompilerServices;
using System.Text;
using System.Transactions;
using static DelegateTest;
using static Program;

internal class Program
{

    private static void Main(string[] args)
    {
        #region 线程停止
        //MyThread my = new MyThread();
        //Thread tr1 = new Thread(my.Run);
        //tr1.Start();
        //for (int i = 0; i < 1000; i++)
        //{
        //    if (i == 600)
        //    {
        //        my.Stop();
        //    }
        //}
        #endregion

        #region 委托以及lambda
        ////使用存在的方法
        //DelegateTest.NoReturnNoPara noReturnAndPara = new DelegateTest.NoReturnNoPara(TestDe1);
        //noReturnAndPara.Invoke();
        ////使用匿名方法（lambda）
        //DelegateTest.WithReturnWithPara withReturnWithPara = (out int x, out int y) =>
        //{
        //    x = 1;
        //    y = 2;
        //    Console.WriteLine("有参数，有返回值的方法");
        //    return x + y;
        //};
        ////int s = withReturnWithPara.Invoke(out int x, out int y);
        ////Console.WriteLine($"有参有值的值为{s}");

        ////多播委托
        //withReturnWithPara += (out int x, out int y) =>
        //{
        //    x = 1;
        //    y = 6;
        //    Console.WriteLine("有参数，有返回值的方法1");
        //    return x + y;
        //};
        //Delegate[] hahah=withReturnWithPara.GetInvocationList();
        //foreach (WithReturnWithPara item in hahah)
        //{
        //    try
        //    {
        //        int result = item.Invoke(out int x,out int y);
        //        Console.WriteLine($"值为{result}");
        //    }catch(Exception ex)
        //    {
        //        throw ex;
        //    }
        //}
        #endregion

        #region 线程礼让
        //MyThread my = new MyThread();
        //Thread tr2 = new Thread(new ThreadStart(my.Run1));
        //Thread tr3 = new Thread(new ThreadStart(my.Run2));
        //tr2.Start();
        //Thread.Yield();
        //tr3.Start();
        #endregion

        #region 线程插队
        //MyThread my = new MyThread();
        //Thread tr5 = new Thread(new ThreadStart(my.Run3));
        //tr5.Start();

        //for (int i = 0; i < 1000; i++)
        //{
        //    Console.WriteLine($"主线程执行中");
        //    tr5.Join();
        //}

        #endregion

        #region 线程状态
        //Thread th = new Thread(() =>
        //{
        //    for (int i = 0; i < 6; i++)
        //    {
        //        Thread.Sleep(1000);
        //    }
        //    Console.WriteLine("````````");
        //});

        ////监视上面的线程
        //ThreadState state = th.ThreadState;
        //Console.WriteLine(state);
        //th.Start();
        //while (state!=ThreadState.Stopped)
        //{
        //    Thread.Sleep(100);
        //    state = th.ThreadState;
        //    Console.WriteLine(state);
        //}

        #endregion

        #region 线程优先级
        //Console.WriteLine(Thread.CurrentThread+"-->"+Thread.CurrentThread.Priority);
        //MyThread mt = new MyThread();

        //Thread th = new Thread(new ThreadStart(mt.Run1));
        //th.Priority = ThreadPriority.Normal;
        //Console.WriteLine(th.Priority + "-->" + th.Priority);
        //th.Start();

        //Thread th2 = new Thread(new ThreadStart(mt.Run1));
        //th2.Priority = ThreadPriority.Highest;
        //th2.Start();
        //Console.WriteLine(th2.Priority + "-->" + th2.Priority);


        #endregion

        #region 守护线程
        //Thread th = new Thread(() =>
        //{
        //    while (true)
        //    {
        //        Console.WriteLine("守护线程");
        //    }

        //});
        //Thread th1 = new Thread(() =>
        //{
        //    for (int i = 0; i < 10; i++)
        //    {
        //        Console.WriteLine("normal线程");
        //    }
        //});
        ////守护线程的用法(守护线程最好的例子就是GC)
        //th.IsBackground = true;
        //th.Start();
        //th1.Start();


        #endregion

        #region 线程锁
        ////第一种不安全案例(使用Monitor上锁)

        //UnsafeTicket ticket = new UnsafeTicket();
        //Thread th1 = new Thread(ticket.run);
        //th1.Name = "zhu";
        //Thread th2 = new Thread(ticket.run);
        //th2.Name = "zhu2";
        //Thread th3 = new Thread(ticket.run);
        //th3.Name = "zhu3";
        //th1.Start();
        //th2.Start();
        //th3.Start();

        ////第二种不安全案例（线程不安全类）
        //List<string> al = new List<string>();
        //for (int i = 0; i < 10000; i++)
        //{

        //    Thread th = new Thread(() =>
        //    {
        //        lock (al)
        //        {
        //            al.Add(Thread.CurrentThread.ToString()!);
        //        }
        //    });

        //    th.Start();

        //}
        //Console.WriteLine(al.Count);


        #endregion

        #region 线程协作(管程模式->生产者消费者)
        //SynContainer syn = new SynContainer();
        //Thread th1 = new(new Productor(syn).Push);
        //th1.Start();
        //Thread th2 = new(new Customer(syn).Pop);
        //th2.Start();

        #endregion

        #region 线程池


        #endregion

        



    }
    
    /// <summary>
    /// 缓冲类
    /// </summary>
    public class SynContainer
    {
        Chicken[] chickens = new Chicken[10];
        int count = 0;
        public void Push(Chicken chicken)
        {
            Monitor.Enter(chickens);
            try
            {
                //如果容器满了，等待消费者消费
                if (count == chickens.Length)
                {

                    Monitor.Wait(chickens);

                }
                //没有满就加工产品

                chickens[count] = chicken;
                count++;
                //加工后通知可以消费了

                Monitor.PulseAll(chickens);

                Console.WriteLine($"生{chicken.id}只鸡");

            }
            finally { Monitor.Exit(chickens); }


        }
        public Chicken Pop()
        {
            try
            {
                Monitor.Enter(chickens);
                //如果不能消费就等待
                if (count == 0)
                {

                    Monitor.Wait(chickens);

                }
                //如果可以消费

                count--;
                Chicken chicken = chickens[count];
                //消费完成通知生产者生产

                Monitor.PulseAll(chickens);
                // Monitor.Exit(chickens);
                return chicken;

            }
            finally { Monitor.Exit(chickens); }

        }

    }


    /// <summary>
    /// 生产者
    /// </summary>
    public class Productor
    {
        SynContainer synContainer;
        public Productor(SynContainer synContainer)
        {
            this.synContainer = synContainer;
        }
        /// <summary>
        /// 生产
        /// </summary>
        public void Push()
        {
            for (int i = 0; i < 100; i++)
            {

                synContainer.Push(new Chicken(i));
                
            }
        }

    }
    /// <summary>
    /// 消费者
    /// </summary>
    public class Customer
    {
        SynContainer synContainer;
        public Customer(SynContainer synContainer)
        {
            this.synContainer = synContainer;
        }
        /// <summary>
        /// 消费
        /// </summary>
        public void Pop()
        {
            for (int i = 0; i < 100; i++)
            {
                Chicken ch = synContainer.Pop();
                Console.WriteLine($"消第{ch.id}只鸡");
            }
        }

    }
    /// <summary>
    /// 产品类
    /// </summary>
    public class Chicken
    {
        public int id;

        public Chicken(int id)
        {
            this.id = id;
        }
    }



    public static bool acquiredLock = false;
    public static object obj = new object();
    #region 线程锁
    public class UnsafeTicket
    {
        private int ticketNums = 20;
        bool flag = true;//外部停止方式
        public void buy()
        {
            try
            {
                Monitor.Enter(obj);
                if (ticketNums <= 0)
                {
                    flag = false;
                    return;
                }
                //模拟延时
                //Thread.Sleep(1000);
                //买票
                Console.WriteLine($"{Thread.CurrentThread.Name}买了第{ticketNums--}");

            }
            catch
            {
                Console.WriteLine("11");
            }
            finally
            {
                Monitor.Exit(obj);
            }

        }
        public void run()
        {
            while (flag)
            {
                buy();
            }
        }
    }


    #endregion




    private static void TestDe1()
    {
        Console.WriteLine($"无返回值无参数");

    }
    private static int TestDe2(out int x, out int y)
    {
        x = 6;
        y = 6;
        Console.WriteLine($"有参数，有返回值的方法2");
        return x + y;

    }

}
public class DelegateTest
{
    public delegate void NoReturnNoPara();
    public delegate int WithReturnWithPara(out int x, out int y);
}

/// <summary>
/// 设置标识位来停止进程
/// </summary>
public class MyThread
{
    private bool _shouldStop;

    public void Run()
    {
        int i = 0;
        while (!_shouldStop)
        {
            Console.WriteLine($"run`````Thread{i++}");
        }
    }
    public void Run1()
    {
        Console.WriteLine($"run`````Thread```A");
        Console.WriteLine($"run`````Thread```A");
        Console.WriteLine($"run`````Thread```A");
        Console.WriteLine($"run`````Thread```A");
        Console.WriteLine($"run`````Thread```A");
        Console.WriteLine($"run`````Thread```A");
    }
    public void Run2()
    {

        Console.WriteLine($"run`````Thread```B");
        Console.WriteLine($"run`````Thread```B");
        Console.WriteLine($"run`````Thread```B");
        Console.WriteLine($"run`````Thread```B");
        Console.WriteLine($"run`````Thread```B");
        Console.WriteLine($"run`````Thread```B");

    }
    public void Run3()
    {

        for (int i = 0; i < 1000; i++)
        {
            Console.WriteLine($"Run3线程执行中");
        }
    }
    public void Stop()
    {
        _shouldStop = true;
    }
}
