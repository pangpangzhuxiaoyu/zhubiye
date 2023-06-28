using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using WebApiLearn.entity;

namespace WebApiLearn.Controllers
{
    [Route("api/[controller]/[action]")]
    [ApiController]
    public class TestController : ControllerBase
    {
        [HttpGet]
        public Person GetPerson()
        {
            return new Person("zhuhe", 18);
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
