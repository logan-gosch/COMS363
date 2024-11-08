SELECT c.number, c.name
FROM courses c
JOIN departments d ON c.department_code = d.code
WHERE d.name = 'Computer Science'
ORDER BY c.number;