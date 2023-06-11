using System;
using System.Collections.Generic;
using System.Text;

namespace MileService
{
    public interface IMailService
    {
        public void Send(string title, string to, string body);
    }
}
