SELECT c.number, c.name, COALESCE(COUNT(r.snum), 0) AS registered_students
FROM courses c
LEFT JOIN register r ON c.number = r.course_number
GROUP BY c.number
ORDER BY c.number;
