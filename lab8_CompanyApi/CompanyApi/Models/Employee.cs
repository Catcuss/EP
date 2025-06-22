using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;

namespace CompanyApi.Models;

[Table("Employee")]
public partial class Employee
{
    [Key]
    public int EmployeeId { get; set; }

    [StringLength(15)]
    [Unicode(false)]
    public string? FirstName { get; set; }

    [StringLength(15)]
    [Unicode(false)]
    public string? LastName { get; set; }

    public int? ManagerId { get; set; }

    [Column(TypeName = "decimal(6, 2)")]
    public decimal? Salary { get; set; }

    [Column(TypeName = "decimal(6, 2)")]
    public decimal? Bonus { get; set; }

    public int? DepartmentId { get; set; }

    [ForeignKey("DepartmentId")]
    [InverseProperty("Employees")]
    public virtual Department? Department { get; set; }

    [InverseProperty("Manager")]
    public virtual ICollection<Employee> Subordinate { get; set; } = new List<Employee>();

    [ForeignKey("ManagerId")]
    [InverseProperty("Subordinate")]
    public virtual Employee? Manager { get; set; }
}
