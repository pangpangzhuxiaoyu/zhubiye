using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lOGGING
{
     class LogText
    {
        private readonly ILogger<LogText> logger;
        public LogText(ILogger<LogText> logger)
        {
            this.logger = logger;
        }

        public void Test()
        {
            logger.LogDebug("进入数据库");
        }
    }
}
