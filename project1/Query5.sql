SELECT dg.name, dg.level
FROM degrees dg
JOIN departments d ON dg.department_code = d.code
WHERE d.name = 'Computer Science'
ORDER BY dg.level;