SELECT s.name, s.snum
FROM students s
JOIN register r ON s.snum = r.snum
JOIN courses c ON r.course_number = c.number
JOIN major m ON s.snum = m.snum
JOIN degrees d ON m.name = d.name AND m.level = d.level
WHERE c.name = 'database' AND d.level IN ('MS', 'PhD')
ORDER BY s.snum;