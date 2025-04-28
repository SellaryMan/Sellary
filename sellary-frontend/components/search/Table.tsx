'use client'
import React, { forwardRef, useState, useEffect } from 'react';

interface DataTableProps {
  data: Array<Record<string, any>>;
  codeField?: string;
  onSelectionChange?: (selectedCode: Array<string | number>) => void;
}

export const Table = forwardRef<
  HTMLTableElement,
  React.TableHTMLAttributes<HTMLTableElement>
>((props, ref) => {
  return <table className="w-full" ref={ref} {...props} />;
});

export const TableHead = forwardRef<
  HTMLTableSectionElement,
  React.TableHTMLAttributes<HTMLTableSectionElement>
>((props, ref) => {
  return <thead ref={ref} {...props} />;
});

export const TableBody = forwardRef<
  HTMLTableSectionElement,
  React.TableHTMLAttributes<HTMLTableSectionElement>
>((props, ref) => {
  return <tbody ref={ref} {...props} />;
});

export const TableRow = forwardRef<
  HTMLTableRowElement,
  React.TableHTMLAttributes<HTMLTableRowElement>
>((props, ref) => {
  return <tr className="" ref={ref} {...props} />;
});

export const TableHeader = forwardRef<
  HTMLTableCellElement,
  React.ThHTMLAttributes<HTMLTableCellElement>
>((props, ref) => {
  return <th className="p-2 w-20 min-w-16 text-left" ref={ref} {...props} />;
});

export const TableCell = forwardRef<
  HTMLTableCellElement,
  React.TdHTMLAttributes<HTMLTableCellElement>
>((props, ref) => {
  return <td className="p-2 w-20 min-w-16" ref={ref} {...props} />;
});

export const DataTable = forwardRef<
  HTMLTableElement,
  DataTableProps & React.TableHTMLAttributes<HTMLTableElement>
>(({ data, codeField = 'productCode', onSelectionChange, ...props }, ref) => {
  if (!data || data.length === 0) {
    return <div>No data available</div>;
  }

  const firstItem = data[0];
  const headers = Object.keys(firstItem);
  
  const [checkedElements, setCheckedElements] = useState<Array<string | number>>([]);

  const [allChecked, setAllChecked] = useState(false);

  useEffect(() => {
    if (onSelectionChange) {
      onSelectionChange(checkedElements);
    }
  }, [checkedElements, onSelectionChange]);

  const handleCheckboxChange = (code: string | number) => {
    setCheckedElements(prev => {
      if (prev.includes(code)) {
        return prev.filter(item => item !== code);
      } else {
        return [...prev, code];
      }
    });
  };

  const handleSelectAll = () => {
    if (allChecked) {
      setCheckedElements([]);
    } else {
      const allCodes = data.map(item => item[codeField]);
      setCheckedElements(allCodes);
    }
    setAllChecked(!allChecked);
  };

  useEffect(() => {
    const allCodes = data.map(item => item[codeField]);
    const isAllChecked = allCodes.length > 0 && allCodes.every(code => checkedElements.includes(code));
    setAllChecked(isAllChecked);
    console.log(checkedElements)
  }, [data, checkedElements, codeField]);

  const checkboxClassName = "h-4 w-4 cursor-pointer bg-white";
  const checkboxStyle = { accentColor: "white" };

  return (
    <Table ref={ref} {...props}>
      <TableHead className="h-1/9 sticky top-0 border-b-2 border-gray-200">
        <TableRow>
          <TableHeader className="w-10 min-w-10 text-center">
            <div className="flex justify-center items-center">
              <input 
                type="checkbox" 
                checked={allChecked}
                onChange={handleSelectAll}
                className={checkboxClassName}
                style={checkboxStyle}
              />
            </div>
          </TableHeader>
          <TableHeader>회사명</TableHeader>
          <TableHeader>상품코드</TableHeader>
          <TableHeader>공급사</TableHeader>
          <TableHeader>바코드</TableHeader>
          <TableHeader>관리키워드1</TableHeader>
          <TableHeader>관리키워드2</TableHeader>
          <TableHeader>관리키워드3</TableHeader>
          <TableHeader>유통기한</TableHeader>
          <TableHeader>제조일자</TableHeader>
          <TableHeader>매입가격</TableHeader>
          <TableHeader>판매가격</TableHeader>
          <TableHeader>총 재고</TableHeader>

          {/* {headers.map((header, Idx) => (
            <TableHeader key={Idx}>{header}</TableHeader>
          ))} */}
        </TableRow>
      </TableHead>
      <TableBody className="h-8/9">
        {data.map((item, rowIdx) => (
          <TableRow key={rowIdx} className="border-b border-gray-300">
            <TableCell className="w-10 min-w-10 text-center">
              <div className="flex justify-center items-center">
                <input 
                  type="checkbox"
                  checked={checkedElements.includes(item[codeField])}
                  onChange={() => handleCheckboxChange(item[codeField])}
                  className={checkboxClassName}
                  style={checkboxStyle}
                />
              </div>
            </TableCell>
            {headers.map((header, cellIdx) => (
              <TableCell key={cellIdx}>{String(item[header] || '')}</TableCell>
            ))}
          </TableRow>
        ))}
      </TableBody>
    </Table>
  );
});

// 기본 props 설정
DataTable.displayName = 'DataTable';