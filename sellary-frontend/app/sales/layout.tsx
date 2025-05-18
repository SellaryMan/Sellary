export default function Layout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <div className="h-full w-full">
      <div className="text-m p-2">상품관리 &gt; 판매상품</div>
      <div className="h-1/9">
      </div>
      <div className="relative mt-4 not-first:h-8/9 w-full overflow-y-auto">
        {children}
      </div>
    </div>
  );
}
