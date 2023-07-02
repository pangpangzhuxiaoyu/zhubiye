namespace WebApiLearn
{
    public class DbDatacs
    {
        //private int _id { get; set; }

        //public DbDatacs(int id)
        //{
        //   this._id = id;
        //}

        public static Task<string?> GetByIdAsync(int id)
        {
            var result = GetById(id);
            return Task.FromResult(result);
        }

        public static string? GetById(int id)
        {
            switch (id)
            {
                case 0:
                    return "11";
                case 1: 
                    return "22";
                default: 
                    return null;


            }
        }


    }
}
