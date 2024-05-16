namespace LinqLearn
{
    internal class Program
    {
        static void Main(string[] args)
        {
            #region 理解Linq的底层逻辑
            //int[] nums = { 65456, 51651, 651, 65, 1, 65 };
            //IEnumerable<int> result = nums.Where(a => a > 2);
            //foreach (int item in result)
            //{
            //    Console.WriteLine(item);
            //}
            ////自己写一个方法来造一个和上面一样的linq
            //IEnumerable<int> result1 = nums.MyWhere( a => a > 2);
            //foreach (int item in result1)
            //{
            //    Console.WriteLine(item);
            //}
            ////上一个方法的高效率版本（使用yield）
            //IEnumerable<int> result2 = nums.MyWhere1(a => a > 2);
            //foreach (int item in result2)
            //{
            //    Console.WriteLine(item);
            //}
            #endregion

            #region linq练习
            List<Person> people = new List<Person>();

            // 添加20条数据
            people.Add(new Person("张三", "男", 25, "北京市海淀区"));
            people.Add(new Person("李四", "女", 18, "上海市徐汇区"));
            people.Add(new Person("王五", "男", 30, "广州市天河区"));
            people.Add(new Person("Tom", "男", 22, "New York"));
            people.Add(new Person("Lisa", "女", 28, "Los Angeles"));
            people.Add(new Person("John", "男", 35, "Chicago"));
            people.Add(new Person("Jack", "男", 27, "Sydney"));
            people.Add(new Person("Lucy", "女", 20, "Melbourne"));
            people.Add(new Person("Mike", "男", 31, "Sydney"));
            people.Add(new Person("Amy", "女", 29, "London"));
            people.Add(new Person("David", "男", 24, "Paris"));
            people.Add(new Person("Emily", "女", 26, "Berlin"));
            people.Add(new Person("Frank", "男", 33, "Tokyo"));
            people.Add(new Person("Grace", "女", 21, "Seoul"));
            people.Add(new Person("Henry", "男", 32, "Beijing"));
            people.Add(new Person("Isabella", "女", 19, "Shanghai"));
            people.Add(new Person("Jason", "男", 23, "Guangzhou"));
            people.Add(new Person("Katherine", "女", 27, "Hong Kong"));
            people.Add(new Person("Leo", "男", 31, "Singapore"));
            people.Add(new Person("Mia", "女", 25, "Bangkok"));

            //Single 有且只有一条数据，多条或者没有都不行
            //Person p = people.Where(e => e.Gender == "男").Single();
            //SingleOrDefault 和上面的区别是这个没有数据的时候会给一个default值
            //Person person = people.Where(e => e.Age >10).SingleOrDefault();

            ////排序语法
            ////升序
            //var items = people.OrderBy(e => e.Age);
            ////降序
            //var items1 = people.OrderByDescending(e => e.Age);
            ////排序后再排序
            //var items2 = people.OrderByDescending(e => e.Age).ThenBy(e => e.Name.Length);
            //foreach (var item in items2)
            //{
            //    Console.WriteLine(item.ToString());
            //}
            ////没用过但是有用的语法
            ////跳过两条后取一条数据
            //people.Skip(2).Take(1);


            ////排序语句1
            //IEnumerable<IGrouping<int,Person>> items = people.GroupBy(e => e.Age);
            //foreach (IGrouping<int, Person> item in items)
            //{
            //    Console.WriteLine(item.Key);
            //    foreach (Person i in item)
            //    {
            //        Console.WriteLine(i.ToString());
            //    }
            //    Console.WriteLine("***************");
            //}

            ////e => new { nianling = e.Age, zhuzhi = e.Address }为匿名类，实际上就是语法糖
            ////Select就相当于SQL，是一种映射，用法和SQL一样
            //var items666 = people.Select(e => new { nianling = e.Age, zhuzhi = e.Address });
            //foreach (var item in items666)
            //{
            //    Console.WriteLine(item.nianling);
            //}




            #endregion

            #region linq题目
            string s = "15,156,1651,651,651,651,65165478,1654,864,651,6,465";
            double d = s.Split(",").Select(e => Convert.ToInt32(e)).Average();
            #endregion
        }





    }

    public static class MyExtension
    {
        public static IEnumerable<int> MyWhere(this IEnumerable<int> items, Func<int, bool> f)
        {
            List<int> li = new List<int>();
            foreach (int i in items)
            {
                if (f(i))
                {
                    li.Add(i);
                }
            }
            return li;
        }
        public static IEnumerable<int> MyWhere1(this IEnumerable<int> items, Func<int, bool> f)
        {

            foreach (int i in items)
            {
                if (f(i))
                {
                    yield return i;
                }
            }

        }





    }


    class Person
    {
        public string Name;
        public string Gender;
        public int Age;
        public string Address;

        public Person(string name, string gender, int age, string address)
        {
            this.Name = name;
            this.Gender = gender;
            this.Age = age;
            this.Address = address;
        }

        public override string ToString()
        {
            string s = this.Age + "," +this.Name + "," + this.Gender + "," + this.Address;
            return s;
        }

    }



}