using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Caching.Memory;
using WebApiLearn.entity;

namespace WebApiLearn.Controllers
{
    [Route("api/[controller]/[action]")]
    [ApiController]
    public class TestController : ControllerBase
    {
        private readonly IMemoryCache memoryCache;
        private readonly ILogger logger;
        public TestController(IMemoryCache memoryCache, ILogger logger)
        {
            this.memoryCache = memoryCache;
            this.logger = logger;
        }

        [HttpGet]
        public Person GetPerson()
        {
            return new Person("zhuhe", 18);
        }

        //设置浏览器缓存查询时间
        [ResponseCache(Duration = 20)]
        [HttpGet]
        public DateTime Now()
        {
            return DateTime.Now;
        }

        /*
         * 内存缓存
         */
        [HttpGet]
        public async Task<ActionResult<string?>> GetString(int id) 
        {
            logger.LogDebug($"开始执行，id：{id}");
            //key(去缓存中取数据，如果能去得到就继续，取不到就调用回调函数去数据库取数据)
            string? s= await memoryCache.GetOrCreateAsync("Hi" + id, async (e) =>
            {
                logger.LogDebug($"缓存中未找到，执行回调函数，id：{id}");

                return await DbDatacs.GetByIdAsync(id);
            });
            logger.LogDebug($"执行结束，数据：{s}");

            if (s == null)
            {
                return NotFound();
            }
            else
            {
                return s;
            }
        }


        [HttpPost]
        public string[] SaveNote(SaveNoteRequest req)
        {
            try
            {
                System.IO.File.WriteAllText(req.Title + ".txt", req.hahah);
                return new string[] { "ok", req.Title };
            }
            catch
            {
                return null;
            }
        }

    }
}
