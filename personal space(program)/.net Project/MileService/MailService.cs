using System;
using System.Collections.Generic;
using System.Security.Cryptography.X509Certificates;
using System.Text;
using ConfigService;
using LogServices;

namespace MileService
{
    public class MailService : IMailService
    {
        private readonly ILogProvider log;
        private readonly IConfigService configS;
        public MailService(ILogProvider log, IConfigService configS)
        {
            this.log = log;
            this.configS = configS;

        }
        public void Send(string title,string to,string body)
        {
            this.log.LogInfo("fasong!");
            string smtpServ = this.configS.GetValue("SmtpServer");
            Console.WriteLine($"zihi{smtpServ}");
            Console.WriteLine($"邮件！{title},{to}");
            this.log.LogInfo("fasongOK!");

        }
    }
}
