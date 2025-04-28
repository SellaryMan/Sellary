import SearchBox from "@/components/search/SearchBox";
export default function Layout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <>
    <div className="h-full w-full">
      <div className="h-1/9">
        <SearchBox/>
      </div>
      <div className="relative h-8/9 w-full overflow-y-auto">
        {children}
      </div>
    </div>


    </>
  );
}
