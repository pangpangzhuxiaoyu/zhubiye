namespace Hi.Utils
{
    public class MyDependency : IMyDependency
    {
        public void WriteMessage(string message)
        {
            Console.WriteLine(message);
        }
    }
}
