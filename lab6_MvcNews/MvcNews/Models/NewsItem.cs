using System.ComponentModel.DataAnnotations;

namespace MvcNews.Models
{
    public class NewsItem
    {
        public int Id { get; set; }

        [DataType(DataType.Date)]
        public DateTime Date { get; set; }

        [Required]
        [StringLength(140, MinimumLength = 5)]
        public string Text { get; set; } = string.Empty;

        [Timestamp]
        public byte[]? RowVersion { get; set; }
    }
}