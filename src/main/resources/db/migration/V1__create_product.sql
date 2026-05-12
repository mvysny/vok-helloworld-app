CREATE TABLE Product(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  sku VARCHAR(40) NOT NULL UNIQUE,
  name VARCHAR(200) NOT NULL,
  category VARCHAR(20) NOT NULL,
  price DECIMAL(10, 2) NOT NULL,
  stock INTEGER NOT NULL,
  unit VARCHAR(20) NOT NULL
);

INSERT INTO Product (sku, name, category, price, stock, unit) VALUES
  ('HX-M6-40',      'Hex bolt M6×40mm, zinc-plated',    'Fasteners',   0.35, 120, 'Each'),
  ('HX-M8-50',      'Hex bolt M8×50mm, stainless',      'Fasteners',   0.85,   0, 'Each'),
  ('NT-M6-BOX100',  'Hex nut M6, zinc-plated, 100/box', 'Fasteners',   4.50,  18, 'Box'),
  ('WD40-400',      'WD-40 lubricant spray 400ml',      'Tools',       7.90,  32, 'Each'),
  ('SD-FLAT-6',     'Screwdriver, flat blade 6mm',      'Tools',       6.50,  12, 'Each'),
  ('PIPE-CU-22',    'Copper pipe Ø22mm',                'Plumbing',   12.40,  45, 'Meter'),
  ('CABLE-3G15',    'Power cable 3G1.5mm²',             'Electrical',  1.80, 200, 'Meter'),
  ('PAINT-WHT-1L',  'Interior paint, white matte 1L',   'Paint',      14.90,   8, 'Each'),
  ('PAINT-WHT-10L', 'Interior paint, white matte 10L',  'Paint',      89.00,   3, 'Each'),
  ('SAND-CONCRETE', 'Concrete sand',                    'Garden',      0.45, 800, 'Kilogram');
