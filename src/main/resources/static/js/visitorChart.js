document.addEventListener('DOMContentLoaded', function() {
    const ctx = document.getElementById('visitorChart')
    const visitorChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', "Test1", "Test2"],
            datasets: [{
                label: 'Monthly Visitors',
                data: [200, 190, 300, 250, 220, 270, 300, 111,222],
                backgroundColor: '#FF6A64'
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: true,
            plugins: {
                legend: {
                    display: false
                },
                tooltip: {
                    mode: 'index',
                    interset: false
                }
            },
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
});

