namespace WebApiLearn
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var builder = WebApplication.CreateBuilder(args);

            // Add services to the container.

            builder.Services.AddControllers();
            // Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
            builder.Services.AddEndpointsApiExplorer();
            builder.Services.AddSwaggerGen();
            /*
             * 内存缓存
             */
            //添加服务器进程的内存缓存
            builder.Services.AddMemoryCache();
            var app = builder.Build();

            // Configure the HTTP request pipeline.
            if (app.Environment.IsDevelopment())
            {
                app.UseSwagger();
                app.UseSwaggerUI();
            }

            app.UseHttpsRedirection();

            app.UseAuthorization();  

            //启用服务器端缓存（一定要加到 app.MapControllers();之前）
            //一般不会用，因为很鸡肋，只要请求端no cache的时候这个就不好用了
            //app.UseResponseCaching();

            app.MapControllers();

            app.Run();
        }
    }
}